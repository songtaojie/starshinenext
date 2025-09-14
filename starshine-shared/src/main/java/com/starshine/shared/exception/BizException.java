package com.starshine.shared.exception;

import lombok.Getter;

/**
 * 业务异常
 * @author songtaojie
 * @since 2025-09-14 周日
 */
@Getter
public class BizException extends RuntimeException {
    private final int code;

    // 如 "USER_NOT_FOUND"
    private final String errorCode;

    public BizException(BizErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.errorCode = errorCode.name();
    }

    public BizException(int code, String errorCode, String message) {
        super(message);
        this.code = code;
        this.errorCode = errorCode;
    }
}
