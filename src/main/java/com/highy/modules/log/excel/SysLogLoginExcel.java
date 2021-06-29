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
 * 登录日志
 *
 * @author jchaoy 453428948@qq.com
 * @since 1.0.0
 */
@Data
public class SysLogLoginExcel {
    @Excel(name = "用户操作")
    private String operation;
    @Excel(name = "状态", replace = {"失败_0", "成功_1", "账号已锁定_1"})
    private Integer status;
    @Excel(name = "User-Agent")
    private String userAgent;
    @Excel(name = "操作IP")
    private String ip;
    @Excel(name = "用户名")
    private String creatorName;
    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

}
