package com.highy.modules.plot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.highy.modules.plot.service.IRenderService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.highy.common.utils.ResponseStatus;
import com.highy.modules.oss.cloud.OSSFactory;
import com.highy.modules.plot.controller.model.AnalysisDataMessage;
import com.highy.modules.plot.controller.model.RestResponse;
import com.highy.modules.utils.SingleRengineBuilder;
 
@RestController
@RequestMapping("/dataAnalysis")
public class DataAnalysisController {

	@Autowired
	IRenderService iRenderService;
	/**
	 * 接收数据
	 * 
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "/analysis")
	@CrossOrigin
	public RestResponse analysis(HttpServletRequest request, AnalysisDataMessage message) {
		return iRenderService.render(request,message);
	}







 
	
	/**
	 * 文件分析
	 * 
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "/fileAnalysis")
	public RestResponse fileAnalysis(@RequestParam( value="files",required=false)MultipartFile dataFile, AnalysisDataMessage message) {
		RestResponse response = new RestResponse<>(); 
		response.setCode(HttpStatus.OK.value());
		response.setStatus(ResponseStatus.S_SUCCESS.getCode());
		String bizContent = "";
		String restMessage = "";
		
		System.out.println("应用程序classes目录:" + Thread.currentThread().getContextClassLoader().getResource("").getPath());
		//
		if(message==null || dataFile==null) {
			response.setStatus(ResponseStatus.S_ERROR.getCode());
			restMessage = bizContent = "数据文件不能为空";
		}else {
			String plotType = message.getPlotType();
			String filePath = message.getFilePath();
			System.out.println("----------filePath:" + filePath);
			if("plot_001".equals(plotType)){
				// 初始化R解析类
				try{
					if( StringUtils.isNotBlank(filePath)){
						Rengine engine = SingleRengineBuilder.getBuilder().GetInstanceDefault();



						// R文件全路径  本地测试环境
//				        String rfilePath = "D:\\app\\Windows10\\R\\venn_loc.R";
						// R文件全路径  服务器环境
						String rfilePath = "/root/R/venn.R";
				        
						System.out.println("Rengine created, waiting for R");
				        // 等待解析类初始化完毕
				        if (!engine.waitForR()) {
				        	response.setStatus(ResponseStatus.S_ERROR.getCode());
				        	restMessage = bizContent = "Cannot load R";
				            System.out.println("Cannot load R");
//				            response.setMessage("Cannot load R");
				            return response;
				        }

//				        //test *****
//						String[] paths =filePath.split("\\\\");
//				        String filename = paths[paths.length-1];
//				        //合成  为主机的存放文件的路径/文件服务器的路径
//
//				        engine.assign("filename",filename);



				        
				        // 将文件全路径复制给R中的一个变量
				        engine.assign("fileName", rfilePath);
				        // 在R中执行文件。执行后，文件中的两个函数加载到R环境中，后续可以直接调用
				        engine.eval("source(fileName)");
				        System.out.println("R文件执行完毕");
				        
						// 转型为MultipartHttpRequest：
//						MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//						MultipartFile dataFile = multipartRequest.getFile("filePath");

						//上传文件
						String extension = FilenameUtils.getExtension(dataFile.getOriginalFilename());
						String url = OSSFactory.build().uploadSuffix(dataFile.getBytes(), extension);
						
						// 将数组复制给R中的变量c1。R中变量无需预先定义
			            engine.assign("K1", url);
						
						// 直接调用无参的函数，将结果保存到一个对象中
			            REXP rexp = engine.eval("venn('"+url+"')");
						System.out.println("REXP =>  "+rexp);
						if(null==rexp){
							response.setStatus(ResponseStatus.S_ERROR.getCode());
							restMessage = bizContent = "生成图片失败！";
						}else{
							bizContent = rexp.asString().replaceAll("/usr/app/", "");
							System.out.println(bizContent);
						}
					} else {
						restMessage = bizContent = "绘图数据与上传附件不能都为空";
						response.setStatus(ResponseStatus.S_ERROR.getCode());
					}
			        
				} catch (Exception e) {
					restMessage = bizContent = "异常：" + e.getMessage();
					response.setStatus(ResponseStatus.S_ERROR.getCode());
					e.getStackTrace();
				} finally{
				}
			}else{
				restMessage = bizContent = "无法获取绘图类型！";
				response.setStatus(ResponseStatus.S_ERROR.getCode());
			}
			
		}
		response.setBizContent(bizContent);
		response.setMessage(restMessage);
		
		return response;
	}

}
