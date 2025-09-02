package com.starshine.infrastructure.service.domain.captcha;

import com.starshine.common.cache.RedisCache;
import com.starshine.domain.captcha.ICaptchaService;
import com.starshine.domain.config.ISysConfigRepository;
import com.starshine.shared.constant.CacheConstants;
import com.starshine.shared.constant.ConfigConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

/**
 * 验证码服务实现类
 * @author songtaojie
 * @version 1.0
 * @since 2025-09-03 上午 周三
 */
@Service
@RequiredArgsConstructor
public class CaptchaServiceImpl implements ICaptchaService {
    private final ISysConfigRepository sysConfigRepository;
    private final RedisCache redisCache;
    @Override
    public void validate(String code, String uuid) {
        // 1. 检查是否启用验证码
        var sysConfig = sysConfigRepository.findByCode(ConfigConstants.SYS_CAPTCHA);
        if (sysConfig == null || !"true".equalsIgnoreCase(sysConfig.getValue())) {
            return; // 未启用，跳过验证
        }

        // 2. 检查验证码是否存在
        String cacheKey = CacheConstants.CAPTCHA_KEY + uuid;
        String captcha = redisCache.getCacheObject(cacheKey);
        if (captcha == null) {
            throw new BadCredentialsException("验证码已失效");
        }

        // 3. 删除验证码（防止重放）
        redisCache.deleteObject(cacheKey);

        // 4. 校验验证码
        if (!captcha.equalsIgnoreCase(code)) {
            throw new BadCredentialsException("验证码错误");
        }
    }
}
