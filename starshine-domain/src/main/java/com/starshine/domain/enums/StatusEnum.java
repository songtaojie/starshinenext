package com.starshine.domain.enums;

/**
 * 状态枚举
 * @author songtaojie
 * @since 2025-09-17 周三
 */
public enum StatusEnum {
    NORMAL("1", "正常"),
    DISABLED("2", "禁用");
    private final String code;
    private final String message;
    StatusEnum(String code, String message) {
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
