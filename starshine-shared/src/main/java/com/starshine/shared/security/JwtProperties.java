package com.starshine.shared.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {
    /**
     * 密钥
     */
    private String secret;
    /**
     * 签发者
     */
    private String issuer;
    /**
     * 接收者
     */
    private String audience;
    /**
     * 访问令牌有效期,分钟
     */
    private long accessExpiration;
    /**
     * 刷新令牌有效期,分钟
      */
    private long refreshExpiration;

    /**
     * 时钟偏差,秒
     */
    private long clockSkew;
}
