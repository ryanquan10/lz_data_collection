/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.message.dao;

import com.highy.common.dao.BaseDao;
import com.highy.modules.message.entity.SysMailTemplateEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 邮件模板
 * 
 * @author jchaoy 453428948@qq.com
 */
@Mapper
public interface SysMailTemplateDao extends BaseDao<SysMailTemplateEntity> {
	
}
