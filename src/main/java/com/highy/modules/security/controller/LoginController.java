/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.security.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.highy.common.exception.ErrorCode;
import com.highy.common.utils.IpUtils;
import com.highy.common.utils.Result;
import com.highy.common.validator.ValidatorUtils;
import com.highy.modules.log.entity.SysLogLoginEntity;
import com.highy.modules.log.enums.LoginOperationEnum;
import com.highy.modules.log.enums.LoginStatusEnum;
import com.highy.modules.log.service.SysLogLoginService;
import com.highy.modules.security.dto.LoginDTO;
import com.highy.modules.security.user.SecurityUser;
import com.highy.modules.security.user.UserDetail;
import com.highy.modules.sys.dto.SysUserDTO;
import com.highy.modules.sys.service.SysUserService;

/**
 * 登录
 * 
 * @author jchaoy 453428948@qq.com
 */
@RestController
@Api(tags="登录管理")
public class LoginController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysLogLoginService sysLogLoginService;
	@Autowired
	private Producer producer;

	@GetMapping("captcha")
	@ApiOperation(value = "验证码", produces="application/octet-stream")
	public void captcha(HttpServletRequest request, HttpServletResponse response)throws IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//生成文字验证码
		String text = producer.createText();
		//生成图片验证码
		BufferedImage image = producer.createImage(text);

		//保存到session
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		out.close();
	}

	@PostMapping("login")
	@ApiOperation(value = "登录")
	public Result login(HttpServletRequest request, @RequestBody LoginDTO login) {
		//效验数据
		ValidatorUtils.validateEntity(login);

		//验证码是否正确
		String kaptcha = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(!login.getCaptcha().equalsIgnoreCase(kaptcha)){
			request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
			return new Result().error(ErrorCode.CAPTCHA_ERROR);
		}

		//用户信息
		SysUserDTO user = sysUserService.getByUsername(login.getUsername());
		if(user == null){
			return new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR);
		}

		SysLogLoginEntity log = new SysLogLoginEntity();
		log.setOperation(LoginOperationEnum.LOGIN.value());
		log.setCreateDate(new Date());
		log.setIp(IpUtils.getIpAddr(request));
		log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
		log.setIp(IpUtils.getIpAddr(request));

		Result result = new Result();
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(login.getUsername(), login.getPassword());
			subject.login(token);

			//登录成功
			log.setStatus(LoginStatusEnum.SUCCESS.value());
			log.setCreator(user.getId());
			log.setCreatorName(user.getUsername());
		}catch (UnknownAccountException e) {
			log.setStatus(LoginStatusEnum.FAIL.value());
			log.setCreatorName(login.getUsername());
			result = new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR);
		}catch (LockedAccountException e) {
			log.setStatus(LoginStatusEnum.LOCK.value());
			log.setCreator(user.getId());
			log.setCreatorName(user.getUsername());
			result = new Result().error(ErrorCode.ACCOUNT_DISABLE);
		}catch (AuthenticationException e) {
			log.setStatus(LoginStatusEnum.FAIL.value());
			log.setCreator(user.getId());
			log.setCreatorName(user.getUsername());
			result = new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR);
		}

		sysLogLoginService.save(log);

		return result;
	}
	
	@PostMapping("register")
	@ApiOperation(value = "注册")
	public Result register(HttpServletRequest request, @RequestBody LoginDTO dto) {
		//表单校验
        ValidatorUtils.validateEntity(dto);
        try {
        	SysUserDTO suDto = new SysUserDTO();
        	suDto.setEmail(dto.getUsername());
        	suDto.setUsername(dto.getUsername());
        	suDto.setRealName(dto.getUsername());
        	suDto.setPassword(dto.getPassword());
        	suDto.setCaptcha(dto.getCaptcha());
        	
			this.sysUserService.register(suDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result().error("注册失败：" + e.getMessage());
		}

        return new Result();
	}

	@PostMapping("logout")
	@ApiOperation(value = "退出")
	public Result logout(HttpServletRequest request) {
		UserDetail user = SecurityUser.getUser();

		//退出
		SecurityUtils.getSubject().logout();

		//用户信息
		SysLogLoginEntity log = new SysLogLoginEntity();
		log.setOperation(LoginOperationEnum.LOGOUT.value());
		log.setIp(IpUtils.getIpAddr(request));
		log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
		log.setIp(IpUtils.getIpAddr(request));
		log.setStatus(LoginStatusEnum.SUCCESS.value());
		log.setCreator(user.getId());
		log.setCreatorName(user.getUsername());
		log.setCreateDate(new Date());
		sysLogLoginService.save(log);

		return new Result();
	}
	
	@PutMapping("sendCaptcha")
	@ApiOperation(value = "发送验证码")
	public Result sendCaptcha(@RequestParam String email) {
		if(StringUtils.isNotBlank(email)){
			//生成文字验证码
			try {
				String captcha = producer.createText();
				this.sysUserService.sendCaptcha(email, captcha);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new Result().error("发送验证码失败：" + e.getMessage());
			}
		}else{
			return new Result().error("邮箱不能为空，请重新输入邮箱地址！");
		}
        return new Result();
	}
	
}