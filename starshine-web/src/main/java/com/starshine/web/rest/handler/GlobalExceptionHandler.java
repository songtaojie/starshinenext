package com.starshine.web.rest.handler;

import com.starshine.common.utils.EscapeUtil;
import com.starshine.common.web.RestfulResult;
import com.starshine.shared.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import com.starshine.common.utils.ConvertUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     *
     * @param e
     */
    @ExceptionHandler(BizException.class)
    public RestfulResult handleException(BizException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.warn("请求地址{}，业务异常: code={}, errorCode={}, message={}", requestURI,e.getCode(), e.getErrorCode(), e.getMessage());
        return RestfulResult.error(e.getCode(),e.getMessage());
    }

    // 定义错误详情
    public record FieldError(String field, String message) {}

    /**
     * 自定义验证异常
     * 表单校验
     */
    @ExceptionHandler(BindException.class)
    public RestfulResult handleBindException(BindException e)
    {
        // 👇 客户端错误，用 WARN 级别，避免刷屏 error 日志
        log.warn("参数校验失败: {}", e.getMessage());

        // 👇 收集所有错误信息
        List<FieldError> errors = e.getBindingResult().getFieldErrors().stream()
                .map(err -> new FieldError(err.getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());
        List<String> errorMessages = errors.stream()
                .map(FieldError::message)
                .collect(Collectors.toList());
        // 👇 使用 400 Bad Request 状态码，并返回所有错误
        return RestfulResult.error(HttpStatus.BAD_REQUEST.value(), String.join("; ", errorMessages))
                .with("errors", errorMessages); // 可选：结构化返回
    }

    /**
     * 请求参数类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public RestfulResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        String value = ConvertUtils.toString(e.getValue());
        if (StringUtils.hasText(value))
        {
            value = EscapeUtil.clean(value);
        }
        log.error("请求参数类型不匹配'{}',发生系统异常.", requestURI, e);
        return RestfulResult.error(HttpStatus.BAD_REQUEST.value(), String.format("请求参数类型不匹配，参数[%s]要求类型为：'%s'，但输入值为：'%s'", e.getName(), e.getRequiredType().getName(), value));
    }

    /**
     * 全局异常处理
     *
     * @param e
     */
    @ExceptionHandler(Exception.class)
    public RestfulResult handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址{}，发生系统异常: ",requestURI, e);
        return RestfulResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),"系统繁忙，请稍后重试");
    }

}
