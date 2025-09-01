package com.starshine.application.service;

import com.starshine.common.cache.RedisCache;
import com.starshine.application.models.LoginRequest;
import com.starshine.shared.constant.CacheConstants;
import com.starshine.shared.constant.ConfigConstants;
import com.starshine.shared.security.AuthenticatedUser;
import com.starshine.shared.security.AuthenticationContextHolder;
import com.starshine.shared.security.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户授权服务
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

    public String login(LoginRequest loginRequest) {
        // 验证码验证
        validateCaptcha(loginRequest);
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
            AuthenticationContextHolder.setAuthentication(authentication);
            authentication = authenticationManager.authenticate(token);
        } catch (Exception e) {
            throw e;
        } finally {
            AuthenticationContextHolder.clear();
        }
        var loginUser =getLoginUser(authentication);
        var token = "";
        return token;
    }

    private void validateCaptcha(LoginRequest loginRequest) {
        boolean captchaEnabled = sysConfigService.getConfigValue(ConfigConstants.SYS_CAPTCHA, Boolean.class);
        if (captchaEnabled) {
            String cacheKey = CacheConstants.CAPTCHA_KEY + loginRequest.uuid();
            String captcha = redisCache.getCacheObject(cacheKey);
            if (captcha == null) {
                throw new BadCredentialsException("验证码已失效");
            }
            redisCache.deleteObject(cacheKey);
            if (!captcha.equals(loginRequest.code())) {
                throw new BadCredentialsException("验证码错误");
            }
        }
    }

    /**
     *
     * @param authentication
     * @return
     */
    private LoginUser getLoginUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof AuthenticatedUser authenticatedUser) {
            return authenticatedUser.getLoginUser();
        } else if (principal instanceof UserDetails userDetails) {
            return new LoginUser(null, userDetails.getUsername(), null, null, null,
                    !userDetails.isAccountNonLocked(), !userDetails.isEnabled(), Set.of(),
                    userDetails.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toSet()));
        } else {
            throw new BadCredentialsException("不支持的 principal 类型：" + principal.getClass().getName());
        }
    }
}
