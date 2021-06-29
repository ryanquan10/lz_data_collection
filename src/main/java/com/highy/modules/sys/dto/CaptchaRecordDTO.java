package com.highy.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 验证码记录
 *
 * @author jchaoy 453428948@qq.com
 * @since 1.0.0 2020-09-23
 */
@Data
@ApiModel(value = "验证码记录")
public class CaptchaRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "发件人邮箱")
	private String senderEmail;

	@ApiModelProperty(value = "收件人邮箱")
	private String recipientEmail;

	@ApiModelProperty(value = "验证码")
	private String captcha;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "更新时间")
	private Date updateTime;


}