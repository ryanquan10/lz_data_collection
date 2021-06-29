///**
// * Copyright (c) 2018 high哥工作室 All rights reserved.
// *
// * https://www.jchaoy.io
// *
// * 版权所有，侵权必究！
// */
//
//package com.highy.modules.message.controller;
//
//import com.google.gson.Gson;
//import com.highy.common.annotation.LogOperation;
//import com.highy.common.constant.Constant;
//import com.highy.common.page.PageData;
//import com.highy.common.utils.Result;
//import com.highy.common.validator.ValidatorUtils;
//import com.highy.common.validator.group.AddGroup;
//import com.highy.common.validator.group.DefaultGroup;
//import com.highy.common.validator.group.UpdateGroup;
//import com.highy.modules.message.dto.SysMailTemplateDTO;
//import com.highy.modules.message.email.EmailConfig;
//import com.highy.modules.message.service.SysMailTemplateService;
//import com.highy.modules.sys.service.SysParamsService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import springfox.documentation.annotations.ApiIgnore;
//
//import java.util.Arrays;
//import java.util.Map;
//
//
///**
// * 邮件模板
// *
// * @author jchaoy 453428948@qq.com
// */
//@RestController
//@RequestMapping("sys/mailtemplate")
//@Api(tags="邮件模板")
//public class MailTemplateController {
//    @Autowired
//    private SysMailTemplateService sysMailTemplateService;
//    @Autowired
//    private SysParamsService sysParamsService;
//
//    private final static String KEY = Constant.MAIL_CONFIG_KEY;
//
//    @GetMapping("page")
//    @ApiOperation("分页")
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
//        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
//        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
//        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String") ,
//        @ApiImplicitParam(name = "name", value = "name", paramType = "query", dataType="String")
//    })
//    @RequiresPermissions("sys:mail:all")
//    public Result<PageData<SysMailTemplateDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
//        PageData<SysMailTemplateDTO> page = sysMailTemplateService.page(params);
//
//        return new Result<PageData<SysMailTemplateDTO>>().ok(page);
//    }
//
//    @GetMapping("/config")
//    @ApiOperation("获取配置信息")
//    @RequiresPermissions("sys:mail:all")
//    public Result<EmailConfig> config(){
//        EmailConfig config = sysParamsService.getValueObject(KEY, EmailConfig.class);
//
//        return new Result<EmailConfig>().ok(config);
//    }
//
//    @PostMapping("/saveConfig")
//    @ApiOperation("保存配置信息")
//    @LogOperation("保存配置信息")
//    @RequiresPermissions("sys:mail:all")
//    public Result saveConfig(@RequestBody EmailConfig config){
//        //校验数据
//        ValidatorUtils.validateEntity(config);
//
//        sysParamsService.updateValueByCode(KEY, new Gson().toJson(config));
//
//        return new Result();
//    }
//
//    @GetMapping("{id}")
//    @ApiOperation("信息")
//    @RequiresPermissions("sys:mail:all")
//    public Result<SysMailTemplateDTO> info(@PathVariable("id") Long id){
//        SysMailTemplateDTO sysMailTemplate = sysMailTemplateService.get(id);
//
//        return new Result<SysMailTemplateDTO>().ok(sysMailTemplate);
//    }
//
//    @PostMapping
//    @ApiOperation("保存")
//    @LogOperation("保存")
//    @RequiresPermissions("sys:mail:all")
//    public Result save(SysMailTemplateDTO dto){
//        //校验类型
//        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
//
//        sysMailTemplateService.save(dto);
//
//        return new Result();
//    }
//
//    @PutMapping
//    @ApiOperation("修改")
//    @LogOperation("修改")
//    @RequiresPermissions("sys:mail:all")
//    public Result update(SysMailTemplateDTO dto){
//        //校验类型
//        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
//
//        sysMailTemplateService.update(dto);
//
//        return new Result();
//    }
//
//    @DeleteMapping
//    @ApiOperation("删除")
//    @LogOperation("删除")
//    @RequiresPermissions("sys:mail:all")
//    public Result delete(@RequestBody Long[] ids){
//        sysMailTemplateService.deleteBatchIds(Arrays.asList(ids));
//
//        return new Result();
//    }
//
//    @PostMapping("/send")
//    @ApiOperation("发送邮件")
//    @LogOperation("发送邮件")
//    @RequiresPermissions("sys:mail:all")
//    public Result send(Long id, String mailTo, String mailCc, String params) throws Exception{
//        boolean flag = sysMailTemplateService.sendMail(id, mailTo, mailCc, params);
//        if(flag){
//            return new Result();
//        }
//
//        return new Result().error("邮件发送失败");
//    }
//    
//    @PostMapping("/bulk")
//    @ApiOperation("群发邮件")
//    @LogOperation("群发邮件")
//    @RequiresPermissions("sys:mail:all")
//    public Result bulk(Long id) throws Exception{
//    	// 群发邮件前，先更新发送标识
//    	this.sysMailTemplateService.updateSendFlag();
//        boolean flag = sysMailTemplateService.bulkMail(id, "A");
//        if(flag){
//            return new Result();
//        }
//
//        return new Result().error("邮件发送失败");
//    }
//    
//
//}