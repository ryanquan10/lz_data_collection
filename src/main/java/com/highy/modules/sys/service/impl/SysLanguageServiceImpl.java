/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.sys.service.impl;

import com.highy.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.highy.modules.sys.dao.SysLanguageDao;
import com.highy.modules.sys.entity.SysLanguageEntity;
import com.highy.modules.sys.service.SysLanguageService;

/**
 * 国际化
 *
 * @author jchaoy 453428948@qq.com
 */
@Service
public class SysLanguageServiceImpl extends BaseServiceImpl<SysLanguageDao, SysLanguageEntity> implements SysLanguageService {

    @Override
    public void saveOrUpdate(String tableName, Long tableId, String fieldName, String fieldValue, String language) {
        SysLanguageEntity entity = new SysLanguageEntity();
        entity.setTableName(tableName);
        entity.setTableId(tableId);
        entity.setFieldName(fieldName);
        entity.setFieldValue(fieldValue);
        entity.setLanguage(language);

        //判断是否有数据
        if(baseDao.getLanguage(entity) == null){
            baseDao.insert(entity);
        }else {
            baseDao.updateLanguage(entity);
        }
    }
}