package com.starshine.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置文件
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-30 下午 周六
 */
@Component
@Data
@ConfigurationProperties(prefix = "starshine")
public class StarshineConfig {

    /**
     * 验证码类型
     */
    private String captchaType;
}

