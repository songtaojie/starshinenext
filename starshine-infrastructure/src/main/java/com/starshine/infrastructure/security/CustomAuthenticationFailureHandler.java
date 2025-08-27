package com.starshine.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starshine.common.utils.StringUtils;
import com.starshine.common.web.RESTfulResult;
import com.starshine.domain.repository.ISysUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
 * 自定义认证失败处理器
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-27 上午 周三
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * 最大失败次数
     */
    private final int MAX_FAILED_COUNT = 5;
    /**
     * 锁定时间
     */
    private final long LOCKOUT_TIME = 15 * 60;

    private final ISysUserRepository sysUserRepository;
    private final ObjectMapper objectMapper;
    public CustomAuthenticationFailureHandler(ISysUserRepository sysUserRepository,
                                              ObjectMapper objectMapper) {
        this.sysUserRepository = sysUserRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String username = request.getParameter("username");
        String errorMessage = exception.getMessage();

        if(!StringUtils.isEmpty( username)) {
            var sysUser =  sysUserRepository.findByUsername(username);
            if(sysUser != null && !sysUser.isLockoutEnabled()) {
                sysUser.setAccessFailedCount(sysUser.getAccessFailedCount() + 1);
                sysUser.setLockoutEnabled(sysUser.getAccessFailedCount() >= MAX_FAILED_COUNT);
                if(sysUser.isLockoutEnabled()) {
                    sysUser.setLockoutEnd(sysUser.getLastPasswordChangeTime().plusSeconds(LOCKOUT_TIME));
                }
                sysUserRepository.lock(sysUser);
            }
        }
        var result = new RESTfulResult(HttpStatus.UNAUTHORIZED, errorMessage);
        objectMapper.writeValue(response.getOutputStream(), result);
    }
}
