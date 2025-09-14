package com.starshine.common.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回结果
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 下午 23:43 周日
 */
public record RestfulResult<T>(Integer code, String message, T data, Long timestamp, Map<String,Object>  extra) implements Serializable {
    // ========== 静态工厂方法 ==========
    /**
     * 创建成功结果
     * @param data
     * @return
     * @param <T>
     */
    public static <T> RestfulResult<T> success(T data) {
        return new RestfulResult<>(HttpStatus.OK.value(), null, data, System.currentTimeMillis(), null   );
    }

    /**
     * 创建错误结果
     * @param code
     * @param message
     * @return
     * @param <T>
     */
    public static <T> RestfulResult<T> error(int code, String message) {
        return new RestfulResult<>(code, message, null, System.currentTimeMillis(), null);
    }

    // ========== 链式添加额外字段 ==========

    /**
     * 添加额外字段
     * @param key
     * @param value
     * @return
     */
    public RestfulResult<T> with(String key, Object value) {
        Map<String, Object> newExtra = new HashMap<>(this.extra != null ? this.extra : Map.of());
        newExtra.put(key, value);
        return new RestfulResult<>(code, message, data, timestamp, newExtra);
    }
}
