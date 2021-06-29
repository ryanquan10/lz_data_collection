/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.message.sms;

import cn.hutool.core.map.MapUtil;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.highy.common.constant.Constant;
import com.highy.common.exception.ErrorCode;
import com.highy.common.exception.RenException;
import com.highy.common.utils.SpringContextUtils;
import com.highy.modules.message.service.SysSmsService;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 腾讯云短信服务
 *
 * @author jchaoy 453428948@qq.com
 */
public class QcloudSmsService extends AbstractSmsService {
    public QcloudSmsService(SmsConfig config){
        this.config = config;
    }

    @Override
    public void sendSms(String mobile, LinkedHashMap<String, String> params) {
        this.sendSms(mobile, params, config.getQcloudSignName(), config.getQcloudTemplateId());
    }

    @Override
    public void sendSms(String mobile, LinkedHashMap<String, String> params, String signName, String template) {
        SmsSingleSender sender = new SmsSingleSender(config.getQcloudAppId(), config.getQcloudAppKey());

        //短信参数
        ArrayList<String> paramsList = new ArrayList<>();
        if(MapUtil.isNotEmpty(params)){
            for(String value : params.values()){
                paramsList.add(value);
            }
        }
        SmsSingleSenderResult result;
        try {
            result = sender.sendWithParam("86", mobile, Integer.parseInt(template), paramsList, signName, null, null);
        } catch (Exception e) {
            throw new RenException(ErrorCode.SEND_SMS_ERROR, e, "");
        }

        int status = Constant.SUCCESS;
        if(result.result != 0){
            status = Constant.FAIL;
        }

        //保存短信记录
        SysSmsService sysSmsService = SpringContextUtils.getBean(SysSmsService.class);
        sysSmsService.save(Constant.SmsService.QCLOUD.getValue(), mobile, params, status);

        if(status == Constant.FAIL){
            throw new RenException(ErrorCode.SEND_SMS_ERROR, result.errMsg);
        }
    }
}
