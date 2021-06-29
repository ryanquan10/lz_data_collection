package com.highy.modules.sys.service;

import com.highy.common.service.CrudService;
import com.highy.modules.sys.dto.CaptchaRecordDTO;
import com.highy.modules.sys.entity.CaptchaRecordEntity;

/**
 * 验证码记录
 *
 * @author jchaoy 453428948@qq.com
 * @since 1.0.0 2020-09-23
 */
public interface CaptchaRecordService extends CrudService<CaptchaRecordEntity, CaptchaRecordDTO> {
	
	// 根据收件人邮箱查找实体
	CaptchaRecordEntity findByRecipient(String email);
}