package com.starshine.shared.security;

import java.io.Serializable;
import java.util.Set;

/**
 * 登录用户
 * @author songtaojie
 * @version 1.0
 * @since 2025-09-01 下午 周一
 */
public record LoginUser(
        Long userId,
        String username,
        String nickName,
        Long tenantId,
        String status,
        boolean locked,
        boolean deleted,
        Set<String> roles,
        Set<String> permissions
) implements Serializable {}
