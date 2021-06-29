/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.log.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 异常日志
 *
 * @author jchaoy 453428948@qq.com
 * @since 1.0.0
 */
@Data
public class SysLogErrorExcel {
    @Excel(name = "请求URI")
    private String requestUri;
    @Excel(name = "请求方式")
    private String requestMethod;
    @Excel(name = "请求参数")
    private String requestParams;
    @Excel(name = "User-Agent")
    private String userAgent;
    @Excel(name = "操作IP")
    private String ip;
    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

}