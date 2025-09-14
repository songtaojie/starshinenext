package com.starshine.shared.exception;

import lombok.Getter;

/**
 * 错误码枚举类
 * 用户服务（10）- 认证模块（01）
 * @author songtaojie
 * @since 2025-09-14 周日
 */
@Getter
public enum BizErrorCode {
    /**
     * 用户不存在
     */
    USER_NOT_FOUND(10010001, "USER_NOT_FOUND", "用户不存在"),
    INVALID_CREDENTIALS(10010002, "INVALID_CREDENTIALS", "用户名或密码错误"),
    /**
     * 认证相关错误
     */
    UNAUTHORIZED(20010001, "UNAUTHORIZED", "未登录或Token已过期");

    private final int code;
    private final String errorCode;
    private final String message;

    BizErrorCode(int code, String errorCode, String message) {
        this.code = code;
        this.errorCode = errorCode;
        this.message = message;
    }
}
