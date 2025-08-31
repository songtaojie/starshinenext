package com.starshine.application.models;

/**
 * 登录参数
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 下午 周六
 */
public record LoginRequest(String username, String password, String code,String uuid) {
}
