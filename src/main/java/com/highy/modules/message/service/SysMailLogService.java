/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.message.service;

import com.highy.common.page.PageData;
import com.highy.common.service.BaseService;
import com.highy.modules.message.dto.SysMailLogDTO;
import com.highy.modules.message.entity.SysMailLogEntity;

import java.util.Map;

/**
 * 邮件发送记录
 *
 * @author jchaoy 453428948@qq.com
 */
public interface SysMailLogService extends BaseService<SysMailLogEntity> {

    PageData<SysMailLogDTO> page(Map<String, Object> params);

    /**
     * 保存邮件发送记录
     * @param templateId  模板ID
     * @param from        发送者
     * @param to          收件人
     * @param cc          抄送
     * @param subject     主题
     * @param content     邮件正文
     * @param status      状态
     */
    void save(Long templateId, String from, String[] to, String[] cc, String subject, String content, Integer status);
}

