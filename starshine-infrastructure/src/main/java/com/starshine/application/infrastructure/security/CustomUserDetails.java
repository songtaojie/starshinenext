package com.starshine.application.infrastructure.security;

import com.starshine.application.domain.model.user.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 自定义用户详情
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-29 下午 周五
 */
public class CustomUserDetails implements UserDetails {
    private final SysUser sysUser;

    public CustomUserDetails(SysUser sysUser) {
        this.sysUser = sysUser;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    /**
     * 获取用户信息
     * @return
     */
    public SysUser getSysUser() {
        return sysUser;
    }

}
