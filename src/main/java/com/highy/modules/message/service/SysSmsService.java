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
import com.highy.modules.message.dto.SysSmsDTO;
import com.highy.modules.message.entity.SysSmsEntity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 短信
 *
 * @author jchaoy 453428948@qq.com
 */
public interface SysSmsService extends BaseService<SysSmsEntity> {

    PageData<SysSmsDTO> page(Map<String, Object> params);

    /**
     * 发送短信
     * @param mobile   手机号
     * @param params   短信参数
     */
    void send(String mobile, String params);

    /**
     * 保存短信发送记录
     * @param platform   平台
     * @param mobile   手机号
     * @param params   短信参数
     * @param status   发送状态
     */
    void save(Integer platform, String mobile, LinkedHashMap<String, String> params, Integer status);
}

