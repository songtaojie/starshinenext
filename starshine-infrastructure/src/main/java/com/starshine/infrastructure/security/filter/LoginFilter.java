package com.starshine.infrastructure.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starshine.common.cache.RedisCache;
import com.starshine.domain.captcha.ICaptchaService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LoginFilter extends OncePerRequestFilter {
    private static final String LOGIN_PATH = "/api/auth/login";
    private static final String HTTP_METHOD = "POST";
    private final ObjectMapper objectMapper;
    private final ICaptchaService captchaService;
    private final RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (isLoginRequest(request)) {
            try {
                validateCaptcha(request);
            } catch (BadCredentialsException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json;charset=UTF-8");

                Map<String, Object> result = Map.of("code", 400, "msg", e.getMessage());
                objectMapper.writeValue(response.getOutputStream(), result);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isLoginRequest(HttpServletRequest request) {
        return LOGIN_PATH.equals(request.getServletPath())
                && HTTP_METHOD.equalsIgnoreCase(request.getMethod());
    }


    private void validateCaptcha(HttpServletRequest request) {
        String code = request.getParameter("code");
        String uuid = request.getParameter("uuid");

        if (uuid == null || code == null) {
            throw new BadCredentialsException("验证码或验证码Key不能为空");
        }

        // ✅ 调用领域服务验证
        captchaService.validate(code,uuid);
    }
}
