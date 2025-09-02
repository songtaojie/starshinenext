package com.starshine.infrastructure.security;

import com.starshine.common.utils.StringUtils;
import com.starshine.domain.user.IUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 自定义认证成功处理器
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-27 下午 周三
 */
@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final IUserRepository sysUserRepository;
    public JwtAuthenticationSuccessHandler(IUserRepository sysUserRepository){
        this.sysUserRepository = sysUserRepository;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
       var username = authentication.getName();
       if(!StringUtils.isEmpty(username)) {
           var sysUser =  sysUserRepository.findByUsername(username);
           if(sysUser != null) {
               sysUser.setLockoutEnabled( false);
               sysUser.setAccessFailedCount(0);
               sysUser.setLockoutEnd(null);
               sysUserRepository.unLock(sysUser);
           }
       }
    }
}
