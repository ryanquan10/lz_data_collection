/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.oss.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * 上传附件信息
 *
 * @author jchaoy 453428948@qq.com
 */
@ApiModel(value = "上传附件信息")
public class UploadFile implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件名")
    @NotBlank(message="{file.name.require}")
    private String name;

    @ApiModelProperty(value = "文件地址(本地)")
    @NotNull(message="{file.url.require}")
    private String url;
    
    @ApiModelProperty(value = "文件大小")
    @NotNull(message="{file.url.size}")
    private Long size;
    
    @ApiModelProperty(value = "文件地址（上传服务器后的地址）")
    @NotNull(message="{file.path.require}")
    private String path;
    
    @ApiModelProperty(value = "")
	private Long id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}