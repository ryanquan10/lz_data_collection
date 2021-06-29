/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

/**
 * excel工具类
 *
 * @author jchaoy 453428948@qq.com
 */
public class ExcelUtils {

    /**
     * Excel导出
     *
     * @param response      response
     * @param fileName      文件名
     * @param list          数据List
     * @param pojoClass     对象Class
     */
    public static void exportExcel(HttpServletResponse response, String fileName, Collection<?> list,
                                     Class<?> pojoClass) throws IOException {
        if(StringUtils.isBlank(fileName)){
            //当前日期
            fileName = DateUtils.format(new Date());
        }

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), pojoClass, list);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

    /**
     * Excel导出，先sourceList转换成List<targetClass>，再导出
     *
     * @param response      response
     * @param fileName      文件名
     * @param sourceList    原数据List
     * @param targetClass   目标对象Class
     */
    public static void exportExcelToTarget(HttpServletResponse response, String fileName, Collection<?> sourceList,
                                     Class<?> targetClass) throws Exception {
        List targetList = new ArrayList<>(sourceList.size());
        for(Object source : sourceList){
            Object target = targetClass.newInstance();
//            if(source instanceof ArticleInfoDTO	){
//            	ArticleInfoDTO aiDto = (ArticleInfoDTO) source;
//            	if(null!=aiDto.getAiDto()){
//            		BeanUtils.copyProperties(aiDto.getAiDto(), target);
//            	}
//            	if(null!=aiDto.getAmDto()){
//            		BeanUtils.copyProperties(aiDto.getAmDto(), target);
//            	}
//            	if(null!=aiDto.getEtDto()){
//            		BeanUtils.copyProperties(aiDto.getEtDto(), target);
//            	}
//            	if(null!=aiDto.getMiDto()){
//            		BeanUtils.copyProperties(aiDto.getMiDto(), target);
//            	}
//				if(null!=aiDto.getPiDto()){
//					BeanUtils.copyProperties(aiDto.getPiDto(), target);	
//				            	}
//				if(null!=aiDto.getPpDto()){
//					BeanUtils.copyProperties(aiDto.getPpDto(), target);
//				}
//            	BeanUtils.copyProperties(aiDto, target);
//            }else{
            	BeanUtils.copyProperties(source, target);
//            }
            targetList.add(target);
        }

        exportExcel(response, fileName, targetList, targetClass);
    }
    
    /**
     * @param resp
     * @param path         文件路径
     * @param downloadName 文件下载时名字
     */
    public static void download(HttpServletResponse resp, String path, String downloadName) {
        String fileName = null;
        try {
            fileName = new String(downloadName.getBytes("GBK"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ///home/tomcat/apache-tomcat-9.0.1/files
//        String realPath = "D:" + File.separator + "apache-tomcat-8.5.15" + File.separator + "files";
//        String realPath=File.separator+"home"+File.separator+"tomcat"+File.separator+"apache-tomcat-9.0.1"+File.separator+"files";
//        String path = realPath + File.separator + name;
        File file = new File(path);
        resp.reset();
        resp.setContentType("application/octet-stream");
        resp.setCharacterEncoding("utf-8");
        resp.setContentLength((int) file.length());
        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = resp.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
            	if(bis!=null){
            		bis.close();
            	}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
