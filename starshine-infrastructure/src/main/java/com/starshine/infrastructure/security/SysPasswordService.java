package com.starshine.infrastructure.security;

import com.starshine.common.cache.RedisCache;
import com.starshine.domain.SysUser;
import com.starshine.repository.ISysUserRepository;
import org.springframework.stereotype.Component;
import org.springframework.

@Component
public class SysPasswordService {
    private final ISysUserRepository sysUserRepository;
    private final RedisCache redisCache;

    public SysPasswordService(ISysUserRepository sysUserRepository, RedisCache redisCache) {
        this.sysUserRepository = sysUserRepository;
        this.redisCache = redisCache;
    }

    public void validate(SysUser sysUser) {
        AuthenticationContextHolder
    }
}
