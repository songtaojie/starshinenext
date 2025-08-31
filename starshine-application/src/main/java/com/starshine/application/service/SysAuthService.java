package com.starshine.application.service;

import com.starshine.application.common.cache.RedisCache;
import com.starshine.application.common.constant.CacheConstants;
import com.starshine.application.common.constant.ConfigConstants;
import com.starshine.application.common.security.AuthenticationContextHolder;
import com.starshine.application.models.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 *  用户授权服务
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-30 下午 周六
 */
@Service
@RequiredArgsConstructor
public class SysAuthService {
    private final AuthenticationManager authenticationManager;
    private final SysUserService sysUserService;
    private final SysConfigService sysConfigService;
    private final RedisCache redisCache;
    public void login(LoginRequest loginRequest){
        // 验证码验证
        validateCaptcha(loginRequest);
        Authentication authentication = null;
        try
        {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
            AuthenticationContextHolder.setAuthentication(authentication);
            authentication = authenticationManager.authenticate(token);
        }
        catch (Exception e)
        {
            throw e;
        }
        finally {
            AuthenticationContextHolder.clear();
        }
        var sysUser = (CustomUserDetails) authentication.getPrincipal();
    }

    private void validateCaptcha(LoginRequest loginRequest) {
        boolean captchaEnabled = sysConfigService.getConfigValue(ConfigConstants.SYS_CAPTCHA, Boolean.class);
        if(captchaEnabled) {
            String cacheKey = CacheConstants.CAPTCHA_KEY+ loginRequest.uuid();
            String captcha = redisCache.getCacheObject(cacheKey);
            if(captcha == null) {
                throw new BadCredentialsException("验证码已失效");
            }
            redisCache.deleteObject(cacheKey);
            if(!captcha.equals(loginRequest.code())) {
                throw new BadCredentialsException("验证码错误");
            }
        }
    }
}
