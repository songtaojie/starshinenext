package com.starshine.repository;

import com.starshine.domain.SysUser;
import org.springframework.lang.Nullable;

/**
 * 用户仓储接口
 * @author starshine
 */
public interface ISysUserRepository {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Nullable
    SysUser findById(Long id);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Nullable
    SysUser findByUsername(String username);
}
