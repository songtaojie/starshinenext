package com.starshine.shared.security;

import java.util.Date;

public class JwtTokenUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // JWT 有效期，示例 1 小时
    private static final long EXPIRATION_MS = 60 * 60 * 1000;

    public static String generateToken(LoginUser loginUser) {
        Date now = new Date();
        Date expire = new Date(now.getTime() + EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(loginUser.username())      // 用户名作为 subject
                .claim("userId", loginUser.userId())   // 自定义 payload
                .claim("tenantId", loginUser.tenantId())
                .claim("roles", loginUser.roles())
                .setIssuedAt(now)
                .setExpiration(expire)
                .signWith(key)
                .compact();
    }
}
