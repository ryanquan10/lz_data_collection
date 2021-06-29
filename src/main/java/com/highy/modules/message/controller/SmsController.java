/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.message.controller;

import com.google.gson.Gson;
import com.highy.common.annotation.LogOperation;
import com.highy.common.constant.Constant;
import com.highy.common.page.PageData;
import com.highy.common.utils.Result;
import com.highy.common.validator.ValidatorUtils;
import com.highy.common.validator.group.AliyunGroup;
import com.highy.common.validator.group.QcloudGroup;
import com.highy.modules.message.dto.SysSmsDTO;
import com.highy.modules.message.service.SysSmsService;
import com.highy.modules.message.sms.SmsConfig;
import com.highy.modules.sys.service.SysParamsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Map;

/**
 * 短信服务
 *
 * @author jchaoy 453428948@qq.com
 */
@RestController
@RequestMapping("sys/sms")
@Api(tags="短信服务")
public class SmsController {
	@Autowired
	private SysSmsService sysSmsService;
    @Autowired
    private SysParamsService sysParamsService;

    private final static String KEY = Constant.SMS_CONFIG_KEY;

	@GetMapping("page")
	@ApiOperation("分页")
	@ApiImplicitParams({
		@ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
		@ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
		@ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
		@ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String") ,
		@ApiImplicitParam(name = "mobile", value = "mobile", paramType = "query", dataType="String"),
		@ApiImplicitParam(name = "status", value = "status", paramType = "query", dataType="String")
	})
	@RequiresPermissions("sys:sms:all")
	public Result<PageData<SysSmsDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
		PageData<SysSmsDTO> page = sysSmsService.page(params);

		return new Result<PageData<SysSmsDTO>>().ok(page);
	}

    @GetMapping("/config")
	@ApiOperation("获取配置短信")
    @RequiresPermissions("sys:sms:all")
    public Result<SmsConfig> config(){
		SmsConfig config = sysParamsService.getValueObject(KEY, SmsConfig.class);

        return new Result<SmsConfig>().ok(config);
    }

	@PostMapping("/saveConfig")
	@ApiOperation("保存配置短信")
	@LogOperation("保存配置短信")
	@RequiresPermissions("sys:sms:all")
	public Result saveConfig(@RequestBody SmsConfig config){
		//校验类型
		ValidatorUtils.validateEntity(config);

		if(config.getPlatform() == Constant.SmsService.ALIYUN.getValue()){
			//校验阿里云数据
			ValidatorUtils.validateEntity(config, AliyunGroup.class);
		}else if(config.getPlatform() == Constant.SmsService.QCLOUD.getValue()){
			//校验腾讯云数据
			ValidatorUtils.validateEntity(config, QcloudGroup.class);
		}

		sysParamsService.updateValueByCode(KEY, new Gson().toJson(config));

		return new Result();
	}

    @PostMapping("/send")
	@ApiOperation("发送短信")
	@LogOperation("发送短信")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mobile", value = "手机好号", paramType = "query", required = true, dataType="String"),
		@ApiImplicitParam(name = "params", value = "参数", paramType = "query", required = true, dataType="String")
	})
    @RequiresPermissions("sys:sms:all")
    public Result send(String mobile, String params){
        sysSmsService.send(mobile, params);

        return new Result();
    }

	@DeleteMapping
	@ApiOperation("删除")
	@LogOperation("删除")
	@RequiresPermissions("sys:sms:all")
	public Result delete(@RequestBody Long[] ids){
		sysSmsService.deleteBatchIds(Arrays.asList(ids));

		return new Result();
	}

}