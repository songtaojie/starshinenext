package com.starshine.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starshine.common.web.RestfulResult;
import com.starshine.shared.exception.BizErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 未授权处理
 * @author songtaojie
 * @version 1.0
 * @since 2025-09-02 下午 周二
 */
@Component
@RequiredArgsConstructor
public class UnauthorizedHandler implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        RestfulResult resTfulResult = RestfulResult.error(BizErrorCode.UNAUTHORIZED.getCode(), "未登录或 Token 已过期");
        objectMapper.writeValue(response.getOutputStream(), resTfulResult);
    }
}
