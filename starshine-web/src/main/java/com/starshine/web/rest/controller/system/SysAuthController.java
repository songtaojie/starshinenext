package com.starshine.application.web.controller.system;

import com.starshine.common.security.AuthenticationContextHolder;
import com.starshine.common.web.BaseController;
import com.starshine.common.web.RestfulResult;
import com.starshine.application.models.LoginRequest;
import com.starshine.application.service.SysConfigService;
import com.starshine.application.service.SysUserService;
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
public class SysAuthController extends BaseController {
    private final SysUserService sysUserService;
    private final AuthenticationManager authenticationManager;
    private final SysConfigService sysConfigService;
    @PostMapping("/login")
    public RestfulResult login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = null;
        log.info("用户登录：{}", loginRequest);
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
            AuthenticationContextHolder.setAuthentication(authentication);
            authentication = authenticationManager.authenticate(authenticationToken);
        }
        catch (Exception e) {
            return RestfulResult.error("用户名或密码错误");
        }
        finally {
            AuthenticationContextHolder.clear();
        }
        return RestfulResult.ok("登录成功", sysUserService.findByUsername(loginRequest.username()));
    }
}

