package com.starshine.shared.constant;

/**
 * 缓存常量
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-30 下午 周六
 */
public class CacheConstants {
    /**
     * 缓存前缀
     */
    public static final String CACHE_PREFIX = "starshine:";

    /**
     * 系统配置缓存
     */
    public static final String SYS_CONFIG_KEY = CACHE_PREFIX + "config:";

    /**
     * 验证码缓存
     */
    public static final String CAPTCHA_KEY = CACHE_PREFIX + "captcha:";
}
