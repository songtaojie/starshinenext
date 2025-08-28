package com.starshine.web.controller.system;

import com.starshine.common.web.RESTfulResult;
import com.starshine.models.LoginRequest;
import com.starshine.service.SysUserService;
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
public class SysAuthController {
    private final SysUserService sysUserService;
    public SysAuthController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @PostMapping("/login")
    public RESTfulResult login(@RequestBody LoginRequest loginRequest) {
        return RESTfulResult.ok("登录成功", sysUserService.findByUsername(loginRequest.username()));
    }
}

