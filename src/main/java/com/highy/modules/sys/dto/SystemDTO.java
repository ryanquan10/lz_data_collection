/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 系统数据
 *
 * @author jchaoy 453428948@qq.com
 * @since 1.0.0
 */
@Data
@ApiModel(value = "系统数据")
public class SystemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long sysTime;
    private String osName;
    private String osArch;
    private String osVersion;
    private String userLanguage;
    private String userDir;
    private Long totalPhysical;
    private Long freePhysical;
    private BigDecimal memoryRate;
    private Integer processors;
    private String jvmName;
    private String javaVersion;
    private String javaHome;
    private Long javaTotalMemory;
    private Long javaFreeMemory;
    private Long javaMaxMemory;
    private String userName;
    private BigDecimal systemCpuLoad;
    private String userTimezone;

}
