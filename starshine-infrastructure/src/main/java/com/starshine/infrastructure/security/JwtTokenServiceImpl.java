package com.starshine.infrastructure.security;


import com.starshine.shared.security.LoginUser;
import com.starshine.shared.security.TokenService;

public class JwtTokenServiceImpl implements TokenService {
    @Override
    public String generateToken(LoginUser loginUser) {
        return "";
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }
}
