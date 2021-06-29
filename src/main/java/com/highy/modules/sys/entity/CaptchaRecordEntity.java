package com.highy.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 验证码记录
 *
 * @author jchaoy 453428948@qq.com
 * @since 1.0.0 2020-09-23
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("captcha_record")
public class CaptchaRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId
    private Long id;
    
    /**
     * 发件人邮箱
     */
	private String senderEmail;
    /**
     * 收件人邮箱
     */
	private String recipientEmail;
    /**
     * 验证码
     */
	private String captcha;
	/**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;
    /**
     * 更新时间
     */
	private Date updateTime;
}