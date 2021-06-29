/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 * <p>
 * https://www.jchaoy.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.highy.common.service;

import com.highy.common.page.PageData;

import java.util.List;
import java.util.Map;

/**
 *  CRUD基础服务接口
 *
 * @author jchaoy 453428948@qq.com
 */
public interface CrudService<T, D> extends BaseService<T> {

    PageData<D> page(Map<String, Object> params);

    List<D> list(Map<String, Object> params);

    D get(Long id);

    void save(D dto);

    void update(D dto);

    void delete(Long[] ids);

}