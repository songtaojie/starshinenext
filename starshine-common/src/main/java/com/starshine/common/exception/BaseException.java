package com.starshine.common.exception;

import com.starshine.common.utils.StringUtils;

/**
 * 基础异常
 * @author startshine
 */
public class BaseException extends RuntimeException {
    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String defaultMessage;

    public BaseException(String module, String code, Object[] args, String defaultMessage) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public BaseException(String module, String code, Object[] args) {
        this(module, code, args, null);
    }
    public BaseException(String module, String defaultMessage) {
        this(module, null, null, defaultMessage);
    }
    public BaseException(String code, Object[] args) {
        this(null, code, args, null);
    }
    public BaseException(String defaultMessage) {
        this(null, defaultMessage);
    }

    public String getMessage(){
        String message = null;
        if(StringUtils.isEmpty(code)){

        }
    }
}
