/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.message.email;

import freemarker.template.Template;
import com.highy.common.constant.Constant;
import com.highy.common.exception.ErrorCode;
import com.highy.common.exception.RenException;
import com.highy.modules.message.entity.SysMailTemplateEntity;
import com.highy.modules.message.service.SysMailLogService;
import com.highy.modules.message.service.SysMailTemplateService;
import com.highy.modules.oss.entity.UploadFile;
import com.highy.modules.sys.service.SysParamsService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import cn.hutool.core.map.MapUtil;

/**
 * 邮件工具类
 *
 * @author jchaoy 453428948@qq.com
 */
@Component
public class EmailUtils {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysParamsService sysParamsService;
    @Autowired
    private SysMailTemplateService sysMailTemplateService;
    @Autowired
    private SysMailLogService sysMailLogService;
    
    private final static String KEY = Constant.MAIL_CONFIG_KEY;
    
    // 邮箱协议常量
    public static final  String MAIL_PROTOCOL_SMTP = "smtp";
    public static final  String MAIL_PROTOCOL_POP3 = "pop3";
    public static final  String MAIL_PROTOCOL_IMAP = "imap";

    private JavaMailSenderImpl createMailSender(EmailConfig config) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(config.getSmtp());
        sender.setPort(config.getPort());
        sender.setUsername(config.getUsername());
        sender.setPassword(config.getPassword());
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", "10000");
        p.setProperty("mail.smtp.auth", "false");
        p.put("mail.smtp.auth", "true");
        // 如果是QQ邮箱发邮件需设置ssl
        if(config.getSmtp().endsWith("qq.com")){
        	p.put("mail.smtp.ssl.enable", "true");  // 设置是否使用ssl安全连接 ---一般都使
        }
        
        sender.setJavaMailProperties(p);
        return sender;
    }

    /**
     * 发送邮件
     * @param templateId   模板ID
     * @param to        收件人
     * @param cc        抄送
     * @param params   模板参数
     * @return true：成功   false：失败
     */
    public boolean sendMail(Long templateId, String[] to, String[] cc, Map<String, Object> params) throws Exception {
        SysMailTemplateEntity template = sysMailTemplateService.selectById(templateId);
        if(template == null){
            throw new RenException(ErrorCode.MAIL_TEMPLATE_NOT_EXISTS);
        }

        EmailConfig config = sysParamsService.getValueObject(KEY, EmailConfig.class);
        JavaMailSenderImpl mailSender = createMailSender(config);
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        //设置utf-8编码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(config.getUsername());

        //收件人
        messageHelper.setTo(to);
        //抄送
        if(cc != null && cc.length > 0){
            messageHelper.setCc(cc);
        }
        //主题
        messageHelper.setSubject(template.getSubject());

        //邮件正文
        String content = getFreemarkerContent(template.getContent(), params);
        messageHelper.setText(content, true);

        int status = Constant.SUCCESS;
        //发送邮件
        try {
            mailSender.send(mimeMessage);
        }catch (Exception e){
            status = Constant.FAIL;
            logger.error("send error", e);
        }

        sysMailLogService.save(templateId, config.getUsername(), to, cc, template.getSubject(), content, status);

        return status == Constant.SUCCESS ? true : false;
    }

    /**
     * 获取Freemarker渲染后的内容
     * @param content   模板内容
     * @param params    参数
     */
    private String getFreemarkerContent(String content, Map<String, Object> params) throws Exception {
        if(MapUtil.isEmpty(params)){
            return content;
        }

        //模板
        StringReader reader = new StringReader(content);
        Template template = new Template("mail", reader, null, "utf-8");

        //渲染模板
        StringWriter sw = new StringWriter();
        template.process(params, sw);

        content = sw.toString();
        IOUtils.closeQuietly(sw);

        return content;
    }

    /**
     * 发送邮件
     * @param to        收件人
     * @param cc        抄送
     * @param subject   主题
     * @param content   邮件正文
     * @return true：成功   false：失败
     */
    public boolean sendMail(String[] to, String[] cc, String subject, String content) throws Exception {
        EmailConfig config = sysParamsService.getValueObject(KEY, EmailConfig.class);
        JavaMailSenderImpl mailSender = createMailSender(config);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //设置utf-8编码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(config.getUsername());

        //收件人
        messageHelper.setTo(to);
        //抄送
        if(cc != null && cc.length > 0){
            messageHelper.setCc(cc);
        }
        //主题
        messageHelper.setSubject(subject);
        //邮件正文
        messageHelper.setText(content, true);

        int status = Constant.SUCCESS;
        //发送邮件
        try {
            mailSender.send(mimeMessage);
        }catch (Exception e){
            status = Constant.FAIL;
            logger.error("send error", e);
        }

        sysMailLogService.save(null, config.getUsername(), to, cc, subject, content, status);

        return status == Constant.SUCCESS ? true : false;
    }
    
    /**
     * 发送邮件
     * @param to        收件人
     * @param cc        抄送
     * @param subject   主题
     * @param content   邮件正文
     * @return true：成功   false：失败
     */
    public boolean userSendMail(String[] to, String[] cc, String subject, String content, List<UploadFile> fileList) throws Exception {
        EmailConfig config = sysParamsService.getValueObject(KEY, EmailConfig.class);
        
        JavaMailSenderImpl mailSender = createMailSender(config);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //设置utf-8编码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(config.getUsername());
        
        //收件人
        messageHelper.setTo(to);
        //抄送
        if(cc != null && cc.length > 0){
            messageHelper.setCc(cc);
        }
        //主题
        messageHelper.setSubject(subject);
        //邮件正文
        messageHelper.setText(content, true);

        int status = Constant.SUCCESS;
        
        // 附件处理
        if(null!=fileList && !fileList.isEmpty() && fileList.size()>0){
        	//整封邮件的MINE消息体
        	MimeMultipart msgMultipart = new MimeMultipart("mixed");//混合的组合关系

        	for(int i=0;i<fileList.size();i++){
        		UploadFile uf = fileList.get(i);
        		MimeBodyPart attch = new MimeBodyPart();
        		msgMultipart.addBodyPart(attch);
        		
        		String fileUrl = "";
        		//*****************************附件***********************************
        		if(uf.getPath().startsWith("upload")){
        			fileUrl = uf.getPath();
        		}else{
        			URL url = new URL(uf.getPath());
        			FileOutputStream fs = null;
        			InputStream inStream = null;
        			try {
        				// 下载网络文件
        				int bytesum = 0;
        				int byteread = 0;
        				String filePath = "upload" + File.separator + "sendMail";
        				File storefile = new File(filePath);   
        				if(!storefile.exists()){
        					storefile.mkdirs();
        				}
        				fileUrl = filePath + File.separator + uf.getName();
        				URLConnection conn = url.openConnection();
        				inStream = conn.getInputStream();
        				fs = new FileOutputStream(fileUrl);
        				
        				byte[] buffer = new byte[1204];
        				int length;
        				while ((byteread = inStream.read(buffer)) != -1) {
        					bytesum += byteread;
//    	                System.out.println(bytesum);
        					fs.write(buffer, 0, byteread);
        				}
        				fs.close();
        			} catch (FileNotFoundException e) {
        				e.printStackTrace();
        			} catch (IOException e) {
        				e.printStackTrace();
        			} finally {
        				if(fs != null){
        					fs.close();
        				}
        				if(inStream != null){
        					inStream.close();
        				}
        			}   
        		}
        		//****************************************************************
        		
        		//把文件，添加到附件1中
        		//数据源
        		DataSource ds1 = new FileDataSource(new File(fileUrl));
        		//数据处理器
        		DataHandler dh1 = new DataHandler(ds1 );
        		//设置第一个附件的数据
        		attch.setDataHandler(dh1);
        		//设置第一个附件的文件名
        		 attch.setFileName(uf.getName());
            }
        	//设置邮件的MINE消息体
        	mimeMessage.setContent(msgMultipart);

        }
        
        
        //发送邮件
        try {
            mailSender.send(mimeMessage);
        }catch (Exception e){
            status = Constant.FAIL;
            logger.error("send error", e);
            throw new Exception(e.getMessage());
        }

//        sysMailLogService.save(null, config.getUsername(), to, cc, subject, content, status);

        return status == Constant.SUCCESS ? true : false;
    }

}
