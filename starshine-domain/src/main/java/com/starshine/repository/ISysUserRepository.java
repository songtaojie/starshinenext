package com.starshine.repository;

import com.starshine.domain.sysuser.SysUser;
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

    /**
     * 更新用户基础信息
     * @param sysUser
     * @return
     */
    int updateUserProfile(SysUser sysUser);

    /**
     * 锁定用户
     * @param sysUser
     * @return
     */
    int lock(SysUser sysUser);

    /**
     * 解锁用户
     * @param sysUser
     * @return
     */
    int unLock(SysUser sysUser);
}
