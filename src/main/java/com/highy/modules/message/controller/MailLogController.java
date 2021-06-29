/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.message.controller;

import com.highy.common.annotation.LogOperation;
import com.highy.common.constant.Constant;
import com.highy.common.page.PageData;
import com.highy.common.utils.Result;
import com.highy.modules.message.dto.SysMailLogDTO;
import com.highy.modules.message.service.SysMailLogService;
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
 * 邮件发送记录
 *
 * @author jchaoy 453428948@qq.com
 */
@RestController
@RequestMapping("sys/maillog")
@Api(tags="邮件发送记录")
public class MailLogController {
    @Autowired
    private SysMailLogService sysMailLogService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = "templateId", value = "templateId", paramType = "query", dataType="String"),
        @ApiImplicitParam(name = "mailTo", value = "mailTo", paramType = "query", dataType="String"),
        @ApiImplicitParam(name = "status", value = "status", paramType = "query", dataType="String")
    })
    @RequiresPermissions("sys:mail:log")
    public Result<PageData<SysMailLogDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<SysMailLogDTO> page = sysMailLogService.page(params);

        return new Result<PageData<SysMailLogDTO>>().ok(page);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:mail:log")
    public Result delete(@RequestBody Long[] ids){
        sysMailLogService.deleteBatchIds(Arrays.asList(ids));

        return new Result();
    }

}