package com.highy.modules.plot.controller.model;


/**
 * restful接口返回，统一封装类
 *
 * @param <T>
 */
public class RestResponse<T> {

    /**
     * http响应码
     */
    private int code;

    /**
     * 状态码
     */
    private String status;

    /**
     * 业务数据
     */
    private T bizContent;

    /**
     * 状态码为error或fail时，对应的错误细信息
     */
    private String message;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBizContent() {
        return bizContent;
    }

    public void setBizContent(T bizContent) {
        this.bizContent = bizContent;
    }
}
