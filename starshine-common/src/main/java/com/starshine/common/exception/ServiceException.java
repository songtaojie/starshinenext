package com.starshine.common.exception;

import lombok.Data;

/**
 * 自定义业务异常
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 下午 周六
 */
@Data
public class ServiceException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 错误信息
     */
    private String message;


    public ServiceException() {
    }
    public ServiceException(String message) {
        super(message);
        this.message = message;
    }

    public ServiceException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }
}
