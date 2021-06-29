/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.sys.service;

import com.highy.common.page.PageData;
import com.highy.common.service.BaseService;
import com.highy.modules.sys.dto.SysDictDTO;
import com.highy.modules.sys.entity.SysDictEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author jchaoy 453428948@qq.com
 * @since 1.0.0
 */
public interface SysDictService extends BaseService<SysDictEntity> {

    PageData<SysDictDTO> page(Map<String, Object> params);

    List<SysDictDTO> list(Map<String, Object> params);
    
    Map<String, List<SysDictDTO>> paramList(Map<String, String> params);

    SysDictDTO get(Long id);

    void save(SysDictDTO dto);

    void update(SysDictDTO dto);

    void delete(Long[] ids);

    void add(SysDictDTO dto);
}