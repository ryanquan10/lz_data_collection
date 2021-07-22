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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
//		return "index";
		return "plot_basic.html";
	}

    @RequestMapping("home.html")
    public String home(){
        return "home";
    }

	@RequestMapping({"login.html"})
	public String login(){
		return "login";
	}

	@RequestMapping({"/forget/{url}.html"})
	public String getbackpwd(
			@PathVariable("url") String url,
			@RequestParam(required = false,name="email")String email,
			RedirectAttributes attribdatautes
	){
//		attribdatautes.addAttribute("email",email);
		System.out.println("/forget/"+url);

		return "/forget/"+url;
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