package com.highy.modules.plot.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.highy.modules.plot.controller.model.Message;
import com.highy.modules.plot.controller.model.RestResponse;
 
@RestController
@RequestMapping("/datacollection/")
public class DataCollectionController {
 

	/**
	 * 接收数据
	 * 
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "/push")
	public RestResponse receive(@RequestBody(required = false) Message message,@RequestHeader(name = "token", required = false) String token) {
		 //
		if(message==null) {
			
		}else {
			
		}
		
		return null;
	}
 

}
