package com.starshine.shared.security;

/**
 * Token 服务
 * @author songtaojie
 * @version 1.0
 * @since 2025-09-01 下午 周一
 */
public interface TokenService {
    /**
     * 生成 JWT Token
     * @param loginUser 用户名
     * @return JWT Token
     */
    String generateToken(LoginUser loginUser);

    /**
     * 验证 Token 是否有效
     * @param token JWT Token
     * @return 是否有效
     */
    boolean validateToken(String token);
}
