/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.sys.controller;

import com.highy.common.annotation.LogOperation;
import com.highy.common.constant.Constant;
import com.highy.common.page.PageData;
import com.highy.common.utils.ExcelUtils;
import com.highy.common.utils.Result;
import com.highy.common.validator.AssertUtils;
import com.highy.common.validator.ValidatorUtils;
import com.highy.common.validator.group.AddGroup;
import com.highy.common.validator.group.DefaultGroup;
import com.highy.common.validator.group.UpdateGroup;
import com.highy.modules.sys.dto.SysParamsDTO;
import com.highy.modules.sys.excel.SysParamsExcel;
import com.highy.modules.sys.service.SysParamsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 参数管理
 *
 * @author jchaoy 453428948@qq.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("sys/params")
@Api(tags="参数管理")
public class SysParamsController {
    @Autowired
    private SysParamsService sysParamsService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = "paramCode", value = "参数编码", paramType = "query", dataType="String")
    })
    public Result<PageData<SysParamsDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<SysParamsDTO> page = sysParamsService.page(params);

        return new Result<PageData<SysParamsDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<SysParamsDTO> get(@PathVariable("id") Long id){
        SysParamsDTO data = sysParamsService.get(id);

        return new Result<SysParamsDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    public Result save(@RequestBody SysParamsDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysParamsService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    public Result update(@RequestBody SysParamsDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysParamsService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        sysParamsService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @ApiImplicitParam(name = "paramCode", value = "参数编码", paramType = "query", dataType="String")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SysParamsDTO> list = sysParamsService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, SysParamsExcel.class);
    }

}