package com.starshine.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.starshine.domain.model.user.SysUser;
import com.starshine.infrastructure.persistence.mapper.SysUserMapper;
import com.starshine.domain.repository.ISysUserRepository;
import org.springframework.stereotype.Repository;

/**
 * 系统用户仓储实现
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 下午 周六
 */
@Repository
// @RequiredArgsConstructor
public class SysUserRepositoryImpl implements ISysUserRepository {

    private final SysUserMapper sysUserMapper;

    public SysUserRepositoryImpl(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    /**
     * 根据id获取用户信息
     * @author starshine
     * @version 1.0
     * @since 2025-08-17 下午 23:10 周日
     */
    @Override
    public SysUser findById(Long id) {
        return sysUserMapper.selectById(id);
    }

    /**
     * 根据用户名获取用户信息
     * @author songtaojie
     * @version 1.0
     * @since 2025-08-23 下午 周六
     */
    @Override
    public SysUser findByUsername(String username) {
        return sysUserMapper.selectOne(
                Wrappers.<SysUser>lambdaQuery()
                        .eq(SysUser::getUsername, username)
        );
    }

    /**
     * 更新用户信息
     * @param sysUser
     * @return
     */
    @Override
    public int updateUserProfile(SysUser sysUser) {
        return sysUserMapper.updateById(sysUser);
    }

    @Override
    public int lock(SysUser sysUser) {
        return sysUserMapper.update(sysUser, Wrappers.<SysUser>lambdaUpdate()
                .eq(SysUser::getId, sysUser.getId())
                .set(SysUser::getAccessFailedCount, sysUser.getAccessFailedCount())
                );
    }

    @Override
    public int unLock(SysUser sysUser) {
        return sysUserMapper.update(sysUser, Wrappers.<SysUser>lambdaUpdate()
                .eq(SysUser::getId, sysUser.getId())
                .set(SysUser::getAccessFailedCount, sysUser.getAccessFailedCount())
        );
    }
}
