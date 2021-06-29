package com.highy.modules.plot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	@RequestMapping("hello")
	public String index() {
		return "hello";
	}

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}
	
	@RequestMapping(value={"plotbasic","plot_basic.html"})
	public String plotbasic() {
//		System.out.println("plotbasic|应用程序classes目录:" + Thread.currentThread().getContextClassLoader().getResource("").getPath());
		return "plot_basic";
	}

}