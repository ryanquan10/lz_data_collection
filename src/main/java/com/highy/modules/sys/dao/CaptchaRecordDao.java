package com.highy.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.highy.common.dao.BaseDao;
import com.highy.modules.sys.entity.CaptchaRecordEntity;

/**
 * 验证码记录
 *
 * @author jchaoy 453428948@qq.com
 * @since 1.0.0 2020-09-23
 */
@Mapper
public interface CaptchaRecordDao extends BaseDao<CaptchaRecordEntity> {
	
	CaptchaRecordEntity findByRecipient(@Param("recipientEmail")String recipientEmail);
}