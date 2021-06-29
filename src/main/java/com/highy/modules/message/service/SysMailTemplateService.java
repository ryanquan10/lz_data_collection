/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.message.service;

import com.highy.common.service.CrudService;
import com.highy.modules.message.dto.SysMailTemplateDTO;
import com.highy.modules.message.entity.SysMailTemplateEntity;

/**
 * 邮件模板
 *
 * @author jchaoy 453428948@qq.com
 */
public interface SysMailTemplateService extends CrudService<SysMailTemplateEntity, SysMailTemplateDTO> {

    /**
     * 发送邮件
     * @param id           邮件模板ID
     * @param mailTo       收件人
     * @param mailCc       抄送
     * @param params       模板参数
     */
    boolean sendMail(Long id, String mailTo, String mailCc, String params) throws Exception;
}