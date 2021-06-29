/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.common.constant;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 常量
 *
 * @author jchaoy 453428948@qq.com
 */
public interface Constant {
    /**
     * 成功
     */
    int SUCCESS = 1;
    /**
     * 失败
     */
    int FAIL = 0;
    /**
     * OK
     */
    String OK = "OK";
    /**
     * 用户标识
     */
    String USER_KEY = "userId";
    /**
     * 菜单根节点标识
     */
    Long MENU_ROOT = 0L;
    /**
     * 部门根节点标识
     */
    Long DEPT_ROOT = 0L;
    /**
     * 数据字典根节点标识
     */
    Long DICT_ROOT = 0L;
    /**
     *  升序
     */
    String ASC = "asc";
    /**
     * 降序
     */
    String DESC = "desc";
    /**
     * 创建时间字段名
     */
    String CREATE_DATE = "create_date";

    /**
     * 创建时间字段名
     */
    String ID = "id";

    /**
     * 数据权限过滤
     */
    String SQL_FILTER = "sqlFilter";

    /**
     * 当前页码
     */
    String PAGE = "page";
    /**
     * 每页显示记录数
     */
    String LIMIT = "limit";
    /**
     * 排序字段
     */
    String ORDER_FIELD = "orderField";
    /**
     * 排序方式
     */
    String ORDER = "order";
    /**
     * token header
     */
    String TOKEN_HEADER = "token";

    /**
     * 云存储配置KEY
     */
    String CLOUD_STORAGE_CONFIG_KEY = "CLOUD_STORAGE_CONFIG_KEY";
    
    /**
     * 附件存放目录
     */
    public final static String MAIL_ATTACH_PATH = "MAIL_ATTACH_PATH";
    
    /**
     * 邮件同步开始时间
     */
    public final static String EMAIL_SYN_START_TIME = "email_syn_start_time";
    
    /**
     * 短信配置KEY
     */
    String SMS_CONFIG_KEY = "SMS_CONFIG_KEY";
    /**
     * 邮件配置KEY
     */
    String MAIL_CONFIG_KEY = "MAIL_CONFIG_KEY";

    /**
     * 定时任务状态
     */
    enum ScheduleStatus {
        /**
         * 暂停
         */
        PAUSE(0),
        /**
         * 正常
         */
        NORMAL(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3),
        /**
         * FASTDFS
         */
        FASTDFS(4),
        /**
         * 本地
         */
        LOCAL(5);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 短信服务商
     */
    enum SmsService {
        /**
         * 阿里云
         */
        ALIYUN(1),
        /**
         * 腾讯云
         */
        QCLOUD(2);

        private int value;

        SmsService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 是或否
     */
    public enum YesOrNo {
        /**
         * 是
         */
        YES("1","是"),
        /**
         * 阿里云
         */
        NO("0","否");

        private String value;
        private String lable;

        YesOrNo(String value, String lable) {
            this.value = value;
            this.lable = lable;
        }

        public String getValue() {
            return value;
        }
        
        public static String getName(String value) {
            if(StringUtils.isBlank(value)){
                return null;
            }
            YesOrNo[] values = YesOrNo.values();
            for (YesOrNo source : values) {
                if (source.value.equals(value)){
                    return source.lable;
                }
            }
            return null;
        }

        /**
         * 返回所有的枚举集合
         */
        public static List<YesOrNo> getAllTypes(){
            return Arrays.asList( YesOrNo.values());
        }
    }
    
    /**
     * 序列类型类型
     */
    public enum SequenceCodeType {
    	SYSNO_TYPE("SYSNO","系统编号类型");

        private String value;
        private String lable;

        SequenceCodeType(String value, String lable) {
            this.value = value;
            this.lable = lable;
        }

        public String getValue() {
            return value;
        }
        
        public static String getName(String value) {
            if(StringUtils.isBlank(value)){
                return null;
            }
            SequenceCodeType[] values = SequenceCodeType.values();
            for (SequenceCodeType source : values) {
                if (source.value.equals(value)){
                    return source.lable;
                }
            }
            return null;
        }

        /**
         * 返回所有的枚举集合
         */
        public static List<SequenceCodeType> getAllTypes(){
            return Arrays.asList( SequenceCodeType.values());
        }
    }
    
    /**
     * 附件类型
     */
    public enum AttachmentType {
        MANUSCRIPT_ATT("1","稿件附件"),
        CONTRACT_ATT("2","合同附件"),
        MATERIAL_ATT("3","资料附件"),
        PROINCOME_ATT("4","项目收入附件"),
        INVOICE_ATT("5","发票附件"),
        PROPAY_ATT("6","项目支出附件"),
        PROVE_ATT("7","证明文件"),
        LAYPS_ATT("8","版面费汇款截图");

        private String value;
        private String lable;

        AttachmentType(String value, String lable) {
            this.value = value;
            this.lable = lable;
        }

        public String getValue() {
            return value;
        }
        
        public static String getName(String value) {
            if(StringUtils.isBlank(value)){
                return null;
            }
            AttachmentType[] values = AttachmentType.values();
            for (AttachmentType source : values) {
                if (source.value.equals(value)){
                    return source.lable;
                }
            }
            return null;
        }

        /**
         * 返回所有的枚举集合
         */
        public static List<AttachmentType> getAllTypes(){
            return Arrays.asList( AttachmentType.values());
        }
    }
    
    /**
     * 邮件状态标识
     */
    public enum EmailFlagType {
    	ANSWERED_FLAG("Flags.Flag.ANSWERED","邮件回复标记，标识邮件是否已回复"),
    	DELETED_FLAG("Flags.Flag.DELETED","邮件删除标记，标识邮件是否需要删除"),
    	DRAFT_FLAG("Flags.Flag.DRAFT","草稿邮件标记，标识邮件是否为草稿"),
    	FLAGGED_FLAG("Flags.Flag.FLAGGED","表示邮件是否为回收站中的邮件"),
    	RECENT_FLAG("Flags.Flag.RECENT","新邮件标记，表示邮件是否为新邮件"),
    	SEEN_FLAG("Flags.Flag.SEEN","邮件阅读标记，标识邮件是否已被阅读"),
    	USER_FLAG("Flags.Flag.USER","底层系统是否支持用户自定义标记，应用程序只能检索这个属性，而不能设置这个属性");

        private String value;
        private String lable;

        EmailFlagType(String value, String lable) {
            this.value = value;
            this.lable = lable;
        }

        public String getValue() {
            return value;
        }
        
        public static String getName(String value) {
            if(StringUtils.isBlank(value)){
                return null;
            }
            EmailFlagType[] values = EmailFlagType.values();
            for (EmailFlagType source : values) {
                if (source.value.equals(value)){
                    return source.lable;
                }
            }
            return null;
        }

        /**
         * 返回所有的枚举集合
         */
        public static List<EmailFlagType> getAllTypes(){
            return Arrays.asList( EmailFlagType.values());
        }
    }
    
    /**
     * 回复标识
     */
    public enum ReplyFlagType {
        UN_REPLAY("0","新稿件"),
        ACCEPT_FLAG("1","接收"),
        REJECT_FLAG("2","拒收"),
        WRITE_FLAG("3","写信"),
        AUTH_NOTICE_SEND("4","授权通知书"),
        AUTH_NOTICE_ACCEPT("41","授权通知书回复"),
        LAYOUT_NOTICE_SEND("5","版面缴纳通知书"),
        LAYOUT_NOTICE_ACCEPT("51","版面缴纳通知书回复"),
        RECEIVE_LETTER_SEND("6","接收函"),
        RECEIVE_LETTER_ACCEPT("61","接收函回复"),
        PROCESSING_REPLY("7","处理中回复"),
        OTHER_ACCEPT("9","其它");

        private String value;
        private String lable;

        ReplyFlagType(String value, String lable) {
            this.value = value;
            this.lable = lable;
        }

        public String getValue() {
            return value;
        }
        
        public String getLabel() {
            return lable;
        }
        
        public static String getName(String value) {
            if(StringUtils.isBlank(value)){
                return null;
            }
            ReplyFlagType[] values = ReplyFlagType.values();
            for (ReplyFlagType source : values) {
                if (source.value.equals(value)){
                    return source.lable;
                }
            }
            return null;
        }

        /**
         * 返回所有的枚举集合
         */
        public static List<ReplyFlagType> getAllTypes(){
            return Arrays.asList( ReplyFlagType.values());
        }
    }
    
    /**
     * 状态标识
     */
    public enum ManuscriptStatusType {
        UN_READ("0","未读"),
        HAVE_READ("1","已读"),
        
        
        REVISED("2","修改中"),
        
        CHUSHEN("GJZT01","初审"),
        XIUTU("GJZT02","修图"),
        RUNSE("GJZT03","润色"),
        FANXIU("GJZT04","返修作者"),
        
        FINISH("10","完成");// 完成

        private String value;
        private String lable;

        ManuscriptStatusType(String value, String lable) {
            this.value = value;
            this.lable = lable;
        }

        public String getValue() {
            return value;
        }
        
        public static String getName(String value) {
            if(StringUtils.isBlank(value)){
                return null;
            }
            ManuscriptStatusType[] values = ManuscriptStatusType.values();
            for (ManuscriptStatusType source : values) {
                if (source.value.equals(value)){
                    return source.lable;
                }
            }
            return null;
        }

        /**
         * 返回所有的枚举集合
         */
        public static List<ManuscriptStatusType> getAllTypes(){
            return Arrays.asList( ManuscriptStatusType.values());
        }
        
        /**
         * 修改中状态
         * @return
         */
        public static List<ManuscriptStatusType> getModifyStatus(){
            return Arrays.asList( ManuscriptStatusType.REVISED);
        }
        
        /**
         * 非修改中状态
         * @return
         */
        public static List<String> getNoModifyStatus(){
            return Arrays.asList( ManuscriptStatusType.UN_READ.value, ManuscriptStatusType.HAVE_READ.value, ManuscriptStatusType.FINISH.value);
        }
    }
    
    /**
     * 查询标识
     */
    public enum StatusFlag {
        /**
         * 新稿件标识
         */
        NEW_FLAG("0","新稿件"),
        /**
         * 处理中标识
         */
        PROCESSING_FLAG("5","处理中"),
        /**
         * 已完成标识
         */
        COMPLETED_FLAG("10","已完成"),

        DELETE_FLAG("-1","已删除");// 已删除的在回收站可查

        private String value;
        private String lable;

        StatusFlag(String value, String lable) {
            this.value = value;
            this.lable = lable;
        }

        public String getValue() {
            return value;
        }
        
        public static String getName(String value) {
            if(StringUtils.isBlank(value)){
                return null;
            }
            StatusFlag[] values = StatusFlag.values();
            for (StatusFlag source : values) {
                if (source.value.equals(value)){
                    return source.lable;
                }
            }
            return null;
        }

        /**
         * 返回所有的枚举集合
         */
        public static List<StatusFlag> getAllTypes(){
            return Arrays.asList( StatusFlag.values());
        }
    }
    
}