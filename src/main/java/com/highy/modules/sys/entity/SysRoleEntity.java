/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.highy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 角色
 * 
 * @author jchaoy 453428948@qq.com
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_role")
public class SysRoleEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 部门ID
	 */
	@TableField(fill = FieldFill.INSERT)
	private Long deptId;
	/**
	 * 更新者
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updater;
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateDate;
}
