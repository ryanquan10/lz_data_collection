/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.sys.dao;

import com.highy.common.dao.BaseDao;
import com.highy.modules.sys.entity.SysLanguageEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 国际化
 * 
 * @author jchaoy 453428948@qq.com
 */
@Mapper
public interface SysLanguageDao extends BaseDao<SysLanguageEntity> {

    SysLanguageEntity getLanguage(SysLanguageEntity entity);

    void updateLanguage(SysLanguageEntity entity);

}