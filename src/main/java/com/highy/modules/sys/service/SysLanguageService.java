/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.sys.service;

import com.highy.common.service.BaseService;
import com.highy.modules.sys.entity.SysLanguageEntity;


/**
 * 国际化
 *
 * @author jchaoy 453428948@qq.com
 */
public interface SysLanguageService extends BaseService<SysLanguageEntity> {

    /**
     * 保存或更新
     * @param tableName   表名
     * @param tableId     表主键
     * @param fieldName   字段名
     * @param fieldValue  字段值
     * @param language    语言
     */
    void saveOrUpdate(String tableName, Long tableId, String fieldName, String fieldValue, String language);
}

