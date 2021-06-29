package com.highy.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.highy.common.service.impl.CrudServiceImpl;
import com.highy.modules.sys.dao.CaptchaRecordDao;
import com.highy.modules.sys.dto.CaptchaRecordDTO;
import com.highy.modules.sys.entity.CaptchaRecordEntity;
import com.highy.modules.sys.service.CaptchaRecordService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 验证码记录
 *
 * @author jchaoy 453428948@qq.com
 * @since 1.0.0 2020-09-23
 */
@Service
public class CaptchaRecordServiceImpl extends CrudServiceImpl<CaptchaRecordDao, CaptchaRecordEntity, CaptchaRecordDTO> implements CaptchaRecordService {

    @Override
    public QueryWrapper<CaptchaRecordEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CaptchaRecordEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

	@Override
	public CaptchaRecordEntity findByRecipient(String email) {
		return this.baseDao.findByRecipient(email);
	}


}