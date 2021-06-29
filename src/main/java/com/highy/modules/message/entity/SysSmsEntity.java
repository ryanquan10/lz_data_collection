/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.message.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.highy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 短信
 * 
 * @author jchaoy 453428948@qq.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_sms")
public class SysSmsEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 平台类型
	 */
	private Integer platform;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 参数1
	 */
	@TableField("params_1")
	private String params1;
	/**
	 * 参数2
	 */
	@TableField("params_2")
	private String params2;
	/**
	 * 参数3
	 */
	@TableField("params_3")
	private String params3;
	/**
	 * 参数4
	 */
	@TableField("params_4")
	private String params4;
	/**
	 * 发送状态  0：失败   1：成功
	 */
	private Integer status;

}