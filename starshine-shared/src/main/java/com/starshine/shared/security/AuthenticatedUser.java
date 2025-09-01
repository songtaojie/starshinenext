package com.starshine.shared.security;

/**
 *  认证 用户
 * @author songtaojie
 * @version 1.0
 * @since 2025-09-01 下午 周一
 */
public interface AuthenticatedUser {

    /**
     * 获取登录用户
     * @return
     */
    LoginUser getLoginUser();
}
