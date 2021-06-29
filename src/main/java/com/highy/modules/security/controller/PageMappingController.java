/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面映射
 *
 * @author jchaoy 453428948@qq.com
 */
@Controller
public class PageMappingController {
	
	@RequestMapping("modules/{module}/{url}.html")
	public String module(@PathVariable("module") String module, @PathVariable("url") String url){
		return "modules/" + module + "/" + url;
	}

	@RequestMapping(value = {"/", "index.html"})
	public String index(){
		return "index";
	}

    @RequestMapping("home.html")
    public String home(){
        return "home";
    }

	@RequestMapping({"login.html"})
	public String login(){
		return "login";
	}
	
	@RequestMapping({"toregister","register.html"})
	public String toregister(){
		return "register";
	}

	@RequestMapping("bmlogin")
	public String bmlogin(){
		return "bmlogin";
	}
	
}