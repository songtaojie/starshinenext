package com.starshine.application.common.security;

import org.springframework.security.core.Authentication;

/**
 * 认证上下文
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-27 下午 周三
 */
public class AuthenticationContextHolder {
    private static final ThreadLocal<Authentication> authentication = new ThreadLocal<>();
    public static void setAuthentication(Authentication authentication) {
        AuthenticationContextHolder.authentication.set(authentication);
    }
    public static Authentication getAuthentication() {
        return authentication.get();
    }
    public static void clear() {
        authentication.remove();
    }
}
