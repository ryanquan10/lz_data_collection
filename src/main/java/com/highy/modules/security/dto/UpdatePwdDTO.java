package com.highy.modules.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value = "forget pwd 表单")
public class UpdatePwdDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("email")
    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "{sysuser.username.require}")
    private String username;

    @JsonProperty("csrfmiddlewaretoken")
    @ApiModelProperty(value = "token")
    @NotBlank(message = "{sysuser.token.require}")
    private String token;


    @JsonProperty("password")
    @ApiModelProperty(value = "pwd")
    @NotBlank(message = "{sysuser.password.require}")
    private String captcha;
}


