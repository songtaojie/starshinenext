package com.starshine.web.controller.system;

import com.starshine.common.security.AuthenticationContextHolder;
import com.starshine.common.web.RESTfulResult;
import com.starshine.models.LoginRequest;
import com.starshine.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 授权控制器
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 下午 周六
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class SysAuthController {
    private final SysUserService sysUserService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public RESTfulResult login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = null;
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
            AuthenticationContextHolder.setAuthentication(authentication);
            authentication = authenticationManager.authenticate(authenticationToken);
        }
        catch (Exception e) {
            return RESTfulResult.error("用户名或密码错误");
        }
        finally {
            AuthenticationContextHolder.clear();
        }
        return RESTfulResult.ok("登录成功", sysUserService.findByUsername(loginRequest.username()));
    }
}

