package com.starshine.service;

import com.starshine.common.security.AuthenticationContextHolder;
import com.starshine.models.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SysAuthService {
    private final AuthenticationManager authenticationManager;
    public SysAuthService(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    public void login(LoginRequest loginRequest){
        // 验证码验证
        validateCaptcha(loginRequest);
        Authentication authentication = null;
        try
        {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
            AuthenticationContextHolder.setAuthentication(authentication);
            authentication = authenticationManager.authenticate(token);
        }
        catch (Exception e)
        {
            if(e instanceof BadCredentialsException) {
                throw e;
            }
        }
        finally {
            AuthenticationContextHolder.clear();
        }
        authentication.getPrincipal();
    }

    private void validateCaptcha(LoginRequest loginRequest) {
    }
}
