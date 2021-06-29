/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.message.sms;

import com.highy.common.constant.Constant;
import com.highy.common.utils.SpringContextUtils;
import com.highy.modules.sys.service.SysParamsService;

/**
 * 短信Factory
 *
 * @author jchaoy 453428948@qq.com
 */
public class SmsFactory {
    private static SysParamsService sysParamsService;

    static {
        SmsFactory.sysParamsService = SpringContextUtils.getBean(SysParamsService.class);
    }

    public static AbstractSmsService build(){
        //获取短信配置信息
        SmsConfig config = sysParamsService.getValueObject(Constant.SMS_CONFIG_KEY, SmsConfig.class);

        if(config.getPlatform() == Constant.SmsService.ALIYUN.getValue()){
            return new AliyunSmsService(config);
        }else if(config.getPlatform() == Constant.SmsService.QCLOUD.getValue()){
            return new QcloudSmsService(config);
        }

        return null;
    }
}
