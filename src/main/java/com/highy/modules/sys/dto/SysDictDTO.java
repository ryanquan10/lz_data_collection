/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.sys.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.highy.common.validator.group.AddGroup;
import com.highy.common.validator.group.DefaultGroup;
import com.highy.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典
 *
 * @author jchaoy 453428948@qq.com
 * @since 1.0.0
 */
@Data
@ApiModel(value = "数据字典")
public class SysDictDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	@Null(message="{id.null}", groups = AddGroup.class)
	@NotNull(message="{id.require}", groups = UpdateGroup.class)
	private Long id;

	@ApiModelProperty(value = "上级ID，一级为0")
	@NotNull(message="{pid.require}", groups = DefaultGroup.class)
	private Long pid;

	@ApiModelProperty(value = "字典类型")
	@NotBlank(message="{sysdict.type.require}", groups = DefaultGroup.class)
	private String dictType;

	@ApiModelProperty(value = "字典名称")
	@NotBlank(message="{sysdict.name.require}", groups = DefaultGroup.class)
	private String dictName;

	@ApiModelProperty(value = "字典值")
	private String dictValue;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "排序")
	@Min(value = 0, message = "{sort.number}", groups = DefaultGroup.class)
	private Integer sort;

	@ApiModelProperty(value = "创建时间")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date createDate;

	@ApiModelProperty(value = "更新时间")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date updateDate;
}