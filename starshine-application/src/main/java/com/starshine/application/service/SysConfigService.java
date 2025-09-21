package com.starshine.application.service;

import com.starshine.common.cache.RedisCache;
import com.starshine.common.utils.ConvertUtils;
import com.starshine.common.utils.StringUtils;
import com.starshine.domain.config.ISysConfigRepository;
import com.starshine.shared.constant.CacheConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 *  系统配置服务
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-30 下午 周六
 */
@Service
@RequiredArgsConstructor
public class SysConfigService {
    /**
     * 系统配置仓储
     */
    private final ISysConfigRepository sysConfigRepository;
    /**
     * 缓存管理器
     */
    private final RedisCache redisCache;

    /**
     * 根据参数键名查询参数值
     *
     * @param code 参数键
     * @return 参数值
     */
    public <T> T getConfigValue(String code,Class<T> targetType) {
        if(StringUtils.isEmpty( code)){
            return null;
        }
        var cacheKey = getCacheKey(code);
        var cacheValue = ConvertUtils.toString(redisCache.getCacheObject(cacheKey)) ;
        if(StringUtils.isEmpty(cacheValue)) {
            var sysConfig = sysConfigRepository.findByCode( code);
            if(sysConfig == null) {
                redisCache.setCacheObject(cacheKey,null, 5L, TimeUnit.MINUTES);
            }else {
                cacheValue = sysConfig.getValue();
                redisCache.setCacheObject(cacheKey,cacheValue,2L,TimeUnit.HOURS);
            }
        }
        if(StringUtils.isEmpty(cacheValue)) return null;

        return ConvertUtils.changeType(cacheValue,targetType);
    }

    /**
     * 获取缓存键名
     *
     * @param code 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String code) {
        return CacheConstants.SYS_CONFIG_KEY + code;
    }
}
