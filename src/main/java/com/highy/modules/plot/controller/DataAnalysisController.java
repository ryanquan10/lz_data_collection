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
	public RestResponse fileAnalysis(HttpServletRequest request,@RequestParam( value="files",required=false)MultipartFile dataFile, AnalysisDataMessage message) {
		 return iRenderService.renderWithFile(request,dataFile,message);
		}

}
