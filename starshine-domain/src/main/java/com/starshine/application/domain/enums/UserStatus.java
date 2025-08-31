package com.starshine.application.domain.enums;

import lombok.Data;

/**
 * 用户状态枚举
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 下午 周六
 */
public enum UserStatus {
    NORMAL("1", "正常"),
    LOCKED("2", "锁定"),
    DISABLED("3", "禁用"),
    DELETED("4", "删除");
    private final String code;
    private final String message;
    UserStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
