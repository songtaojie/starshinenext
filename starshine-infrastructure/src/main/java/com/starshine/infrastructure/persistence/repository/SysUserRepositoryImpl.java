package com.starshine.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.starshine.domain.user.User;
import com.starshine.infrastructure.persistence.converter.UserConverter;
import com.starshine.infrastructure.persistence.mapper.SysUserMapper;
import com.starshine.domain.user.IUserRepository;
import com.starshine.infrastructure.persistence.po.SysUserPO;
import org.springframework.stereotype.Repository;

/**
 * 系统用户仓储实现
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 下午 周六
 */
@Repository
// @RequiredArgsConstructor
public class SysUserRepositoryImpl implements IUserRepository {

    private final SysUserMapper sysUserMapper;
    private final UserConverter userConverter;

    public SysUserRepositoryImpl(SysUserMapper sysUserMapper, UserConverter userConverter) {
        this.sysUserMapper = sysUserMapper;
        this.userConverter = userConverter;
    }

    /**
     * 根据id获取用户信息
     * @author starshine
     * @version 1.0
     * @since 2025-08-17 下午 23:10 周日
     */
    @Override
    public User findById(Long id) {
        return userConverter.toDomain(sysUserMapper.selectById(id)) ;
    }

    /**
     * 根据用户名获取用户信息
     * @author songtaojie
     * @version 1.0
     * @since 2025-08-23 下午 周六
     */
    @Override
    public User findByUsername(String username) {
        var sysUserPO = sysUserMapper.selectOne(
                Wrappers.<SysUserPO>lambdaQuery()
                        .eq(SysUserPO::getUsername, username));
        return userConverter.toDomain(sysUserPO);
    }

    /**
     * 更新用户信息
     * @param sysUser
     * @return
     */
    @Override
    public int updateUserProfile(User sysUser) {
        var sysUserPO = userConverter.toPO(sysUser);
        return sysUserMapper.updateById(sysUserPO);
    }

    @Override
    public int lock(User sysUser) {
        var sysUserPO = userConverter.toPO(sysUser);
        return sysUserMapper.update(sysUserPO, Wrappers.<SysUserPO>lambdaUpdate()
                .eq(SysUserPO::getId, sysUser.getId())
                .set(SysUserPO::getAccessFailedCount, sysUser.getAccessFailedCount())
                );
    }

    @Override
    public int unLock(User sysUser) {
        var sysUserPO = userConverter.toPO(sysUser);
        return sysUserMapper.update(sysUserPO, Wrappers.<SysUserPO>lambdaUpdate()
                .eq(SysUserPO::getId, sysUser.getId())
                .set(SysUserPO::getAccessFailedCount, sysUser.getAccessFailedCount())
        );
    }
}
