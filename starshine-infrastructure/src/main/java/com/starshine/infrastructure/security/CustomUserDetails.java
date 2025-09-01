package com.starshine.infrastructure.security;

import com.starshine.domain.model.user.SysUser;
import com.starshine.shared.security.AuthenticatedUser;
import com.starshine.shared.security.LoginUser;
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
public class CustomUserDetails implements UserDetails, AuthenticatedUser {
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return !sysUser.isLockoutEnabled();
    }

    /**
     * 指定用户密码是否过期,过期的用户无法进行身份验证
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 获取用户是否可用,禁用的用户无法进行身份验证
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return !sysUser.isDeleted();
    }

    /**
     * 获取用户信息
     * @return
     */
    public SysUser getSysUser() {
        return sysUser;
    }

    @Override
    public LoginUser getLoginUser() {
        return new LoginUser(sysUser.getId(), sysUser.getUsername(), sysUser.getName(),sysUser.getTenantId(),
                sysUser.getStatus(),sysUser.isLockoutEnabled(),sysUser.isDeleted(),null,null);
    }
}
