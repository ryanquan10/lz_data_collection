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
import com.highy.common.utils.Result;
import com.highy.common.validator.AssertUtils;
import com.highy.common.validator.ValidatorUtils;
import com.highy.common.validator.group.DefaultGroup;
import com.highy.common.validator.group.UpdateGroup;
import com.highy.modules.sys.dto.SysDictDTO;
import com.highy.modules.sys.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author jchaoy 453428948@qq.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("sys/dict")
@Api(tags="数据字典")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    @GetMapping("page")
    @ApiOperation("字典分类")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = "dictType", value = "字典类型", paramType = "query", dataType="String"),
        @ApiImplicitParam(name = "dictName", value = "字典名称", paramType = "query", dataType="String")
    })
    public Result<PageData<SysDictDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        //字典分类
        PageData<SysDictDTO> page = sysDictService.page(params);

        return new Result<PageData<SysDictDTO>>().ok(page);
    }

    @GetMapping("list")
    @ApiOperation("字典分类数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "dictName", value = "字典名称", paramType = "query", dataType="String"),
        @ApiImplicitParam(name = "dictValue", value = "字典值", paramType = "query", dataType="String")
    })
    public Result<List<SysDictDTO>> list(@ApiIgnore @RequestParam Map<String, Object> params){
        //字典分类数据
        List<SysDictDTO> list = sysDictService.list(params);

        return new Result<List<SysDictDTO>>().ok(list);
    }
    
    @GetMapping("paramList")
    @ApiOperation("字典分类数据")
    public Result<Map<String, List<SysDictDTO>>> paramList(@ApiIgnore @RequestParam  Map<String, String> params){
    	//字典分类数据
    	Map<String, List<SysDictDTO>> resultMap = new HashMap<String, List<SysDictDTO>>();
    	if(null!=params){
    		//字典分类数据
			resultMap = sysDictService.paramList(params);
    	}

        return new Result<Map<String, List<SysDictDTO>>>().ok(resultMap);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<SysDictDTO> get(@PathVariable("id") Long id){
        SysDictDTO data = sysDictService.get(id);

        return new Result<SysDictDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    public Result save(@RequestBody SysDictDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        sysDictService.save(dto);

        return new Result();
    }
    
    @PostMapping("add")
    @ApiOperation("新增")
    @LogOperation("新增")
    public Result add(@RequestBody SysDictDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        sysDictService.add(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    public Result update(@RequestBody SysDictDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysDictService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        sysDictService.delete(ids);

        return new Result();
    }

}