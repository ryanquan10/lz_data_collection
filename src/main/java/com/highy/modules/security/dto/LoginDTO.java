/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录表单
 *
 * @author jchaoy 453428948@qq.com
 */
@Data
@ApiModel(value = "登录表单")
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message="{sysuser.username.require}")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="{sysuser.password.require}")
    private String password;

    @ApiModelProperty(value = "验证码")
    @NotBlank(message="{sysuser.captcha.require}")
    private String captcha;

}