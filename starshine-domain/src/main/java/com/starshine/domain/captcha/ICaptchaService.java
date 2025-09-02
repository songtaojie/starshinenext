package com.starshine.domain.captcha;

/**
 * 验证码服务接口
 * @author songtaojie
 * @version 1.0
 * @since 2025-09-03 上午 周三
 */
public interface ICaptchaService {

    /**
     * 验证验证码
     * @param code 用户输入的验证码
     * @param uuid 验证码唯一标识
     */
    void validate(String code, String uuid);
}
