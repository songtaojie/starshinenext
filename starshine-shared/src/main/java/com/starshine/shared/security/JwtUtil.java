package com.starshine.shared.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * JWT 工具类
 * @author songtaojie
 * @since 2025-09-17 周三
 */
@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final RedisTemplate<String, String> redisTemplate;
    private final String REFRESH_TOKEN_BLACKLIST_PREFIX = "BLACKLIST_REFRESH_TOKEN:";

    private final JwtProperties jwtProperties;
    // 刷新 Token 的 claim 名
    private final String[] REFRESH_CLAIMS = {"f", "e", "s", "l", "k"};

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 生成 Access Token
     */
    public String generateAccessToken(Map<String, Object> claims) {
        long now = System.currentTimeMillis();
        Date issuedAt = new Date(now);
        Date expiration = new Date(now + TimeUnit.MINUTES.toMillis(jwtProperties.getAccessExpiration()));

        var builder = Jwts.builder();
        builder.audience()
                .add(jwtProperties.getAudience());
        return Jwts.builder()
                .claims(claims)
                .issuer(jwtProperties.getIssuer())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(getSecretKey(), Jwts.SIG.HS256)
                .compact();
    }

    /**
     * 生成 Refresh Token（嵌入原 Access Token 的片段）
     */
    public String generateRefreshToken(String accessToken) {
        String[] parts = accessToken.split("\\.");
        if (parts.length != 3) throw new IllegalArgumentException("Invalid access token");

        int s = RandomUtils.nextInt(10, parts[1].length() / 2 + 2);
        int l = RandomUtils.nextInt(3, 13);

        String k = parts[1].substring(s, s + l);

        Map<String, Object> payload = Map.of(
                "f", parts[0],
                "e", parts[2],
                "s", s,
                "l", l,
                "k", k
        );

        long expiration = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(jwtProperties.getRefreshExpiration());
        return Jwts.builder()
                .claims(payload)
                .expiration(new Date(expiration))
                .signWith(getSecretKey())
                .compact();
    }

    /**
     * 用过期 Token 和 Refresh Token 换取新 Token
     */
    public String exchange(String expiredToken, String refreshToken, Long newExpirationMinutes) {
        // 1. 验证原 Token 是否已过期（不验证签名）
        Claims accessClaims = parseClaims(expiredToken);
        if (accessClaims != null && isTokenValid(accessClaims)) {
            return null; // 未过期，不能刷新
        }

        // 2. 验证 Refresh Token
        Claims refreshClaims = validateToken(refreshToken);
        if (refreshClaims == null) return null;

        // 3. 拆分原 Token
        String[] parts = expiredToken.split("\\.");
        if (parts.length != 3) return null;

        // 4. 校验 Refresh Token 中的片段是否匹配
        if (!parts[0].equals(refreshClaims.get("f"))) return null;
        if (!parts[2].equals(refreshClaims.get("e"))) return null;
        int s = refreshClaims.get("s", Integer.class);
        int l = refreshClaims.get("l", Integer.class);
        if (!parts[1].substring(s, s + l).equals(refreshClaims.get("k"))) return null;

        // 5. 获取原 Token 的 payload（不含 iat/nbf/exp）
        Claims originalClaims = parseClaims(expiredToken);

        Map<String, Object> newClaims = new HashMap<>();
        for (Map.Entry<String, Object> entry : originalClaims.entrySet()) {
            if (!Set.of(Claims.ISSUED_AT, Claims.NOT_BEFORE, Claims.EXPIRATION).contains(entry.getKey())) {
                newClaims.put(entry.getKey(), entry.getValue());
            }
        }

        // 6. 检查是否已刷新过（黑名单）
        String blacklistKey = REFRESH_TOKEN_BLACKLIST_PREFIX + refreshToken;
        if (Boolean.TRUE.equals(redisTemplate.hasKey(blacklistKey))) {
            Long expireTime = redisTemplate.getExpire(blacklistKey, TimeUnit.SECONDS);
            if (expireTime == null || expireTime > jwtProperties.getClockSkew()) {
                return null; // 已刷新，且超出容差
            }
        }

        // 7. 生成新 Access Token
        String newToken = generateAccessToken(newClaims);

        // 8. 将 refresh token 加入黑名单（防止重复使用）
        long refreshExp = refreshClaims.getExpiration().getTime();
        redisTemplate.opsForValue().set(
                blacklistKey,
                String.valueOf(System.currentTimeMillis()),
                Duration.ofMillis(refreshExp - System.currentTimeMillis())
        );

        return newToken;
    }

    /**
     * 验证 Token 并返回 Claims
     */
    public Claims validateToken(String token) {
        return parseClaims(token);
    }

    /**
     * 解析 Token（不校验签名）
     */
    public Claims parseClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSecretKey())
                    .clockSkewSeconds(jwtProperties.getClockSkew())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            return null;
        }
    }

    private boolean isTokenValid(Claims claims) {
        Date expiration = claims.getExpiration();
        return expiration == null || expiration.after(new Date());
    }

}
