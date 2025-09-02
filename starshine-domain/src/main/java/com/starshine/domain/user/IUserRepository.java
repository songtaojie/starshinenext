package com.starshine.domain.user;

import org.springframework.lang.Nullable;

/**
 * 用户仓储接口
 * @author starshine
 */
public interface IUserRepository {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Nullable
    User findById(Long id);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Nullable
    User findByUsername(String username);

    /**
     * 更新用户基础信息
     * @param sysUser
     * @return
     */
    int updateUserProfile(User sysUser);

    /**
     * 锁定用户
     * @param sysUser
     * @return
     */
    int lock(User sysUser);

    /**
     * 解锁用户
     * @param sysUser
     * @return
     */
    int unLock(User sysUser);
}
