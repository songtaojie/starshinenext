package com.starshine.service;

import com.starshine.domain.SysUser;

/**
 * 系统用户服务
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 下午 23:12 周日
 */
public interface ISysUserService {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    SysUser findById(Long id);
}
