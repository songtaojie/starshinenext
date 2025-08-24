package com.starshine.web.controller.system;

import com.starshine.common.web.RESTfulResult;
import com.starshine.service.ISysUserService;
import com.starshine.service.models.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 授权控制器
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 下午 周六
 */
@RestController
public class SysAuthController {
    private final ISysUserService sysUserService;
    public SysAuthController(ISysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @PostMapping("/login")
    public RESTfulResult login(@RequestBody LoginRequest loginRequest) {
        SysUser sysUser = sysUserService.findById(1L);
    }
}

