package com.starshine.common.web;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * 通用的返回结果
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 下午 23:43 周日
 */
public class RESTfulResult extends HashMap<String, Object> {
    public  static final String STATUS = "status";

    /**
     * 状态标签
     */
    public static final String CODE_TAG = "code";
    /**
     * 消息标签
     */
    public static final String MESSAGE_TAG = "message";
    /**
     * 数据标签
     */
    public static final String DATA_TAG = "data";

    public RESTfulResult() {
        put(STATUS, true);
    }
    public RESTfulResult(String message) {
        this();
        put(MESSAGE_TAG, message);
    }
    public RESTfulResult(int code, String message) {
        this(message);
        put(CODE_TAG, code);
    }
    public RESTfulResult(int code, String message, Object data) {
        this(code, message);
        put(DATA_TAG, data);
    }

    /**
     * 返回成功
     * @return
     */
    public static RESTfulResult ok() {
        return new RESTfulResult();
    }

    public static RESTfulResult ok(Object data) {
        return new RESTfulResult(HttpStatus.OK);
    }
}
