package com.starshine.domain.enums;

/**
 * 配置类型枚举
 * @author songtaojie
 * @since 2025-09-17 周三
 */
public enum ConfigType {
    SYSTEM("1", "正常"),
    TENANT("2", "禁用");
    private final String code;
    private final String message;
    ConfigType(String code, String message) {
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
