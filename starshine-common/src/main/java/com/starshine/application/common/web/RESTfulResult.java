package com.starshine.application.common.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * 通用的返回结果
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 下午 23:43 周日
 */
public class RESTfulResult extends HashMap<String, Object> {
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

    /**
     * 时间戳标签
     */
    public static final String TIMESTAMP_TAG = "timestamp";


    public RESTfulResult() {
        put(TIMESTAMP_TAG, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
    public RESTfulResult(String message) {
        this();
        put(MESSAGE_TAG, message);
    }
    public RESTfulResult(HttpStatusCode code, String message) {
        this(message);
        put(CODE_TAG, code);
    }
    public RESTfulResult(HttpStatusCode code, String message, Object data) {
        this(code, message);
        put(DATA_TAG, data);
    }

    /**
     * 返回成功
     * @return
     */
    public static RESTfulResult ok() {
        return new RESTfulResult(HttpStatus.OK, "操作成功");
    }

    /**
     * 返回成功
     * @param data  数据
     * @return
     */
    public static RESTfulResult ok(Object data) {
        return new RESTfulResult(HttpStatus.OK, "操作成功", data);
    }

    /**
     * 带消息的返回成功
     * @param message 消息
     * @param data 数据
     * @return
     */
    public static RESTfulResult ok(String message, Object data) {
        return new RESTfulResult(HttpStatus.OK, message, data);
    }

    /**
     * 带消息的返回失败
     * @param message 错误消息
     * @return
     */
    public static  RESTfulResult error(String message) {
        return new RESTfulResult(HttpStatus.BAD_REQUEST, message);
    }

    /**
     * 方便链式调用
     * @param key
     * @param value
     * @return
     */
    public RESTfulResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
