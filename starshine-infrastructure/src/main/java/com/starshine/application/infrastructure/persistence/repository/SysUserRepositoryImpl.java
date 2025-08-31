package com.starshine.application.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.starshine.application.domain.model.user.SysUser;
import com.starshine.application.infrastructure.persistence.converter.UserConverter;
import com.starshine.application.infrastructure.persistence.mapper.SysUserMapper;
import com.starshine.application.domain.repository.ISysUserRepository;
import com.starshine.application.infrastructure.persistence.po.SysUserPO;
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
    public SysUser findById(Long id) {
        return userConverter.toDomain(sysUserMapper.selectById(id)) ;
    }

    /**
     * 根据用户名获取用户信息
     * @author songtaojie
     * @version 1.0
     * @since 2025-08-23 下午 周六
     */
    @Override
    public SysUser findByUsername(String username) {
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
    public int updateUserProfile(SysUser sysUser) {
        var sysUserPO = userConverter.toPO(sysUser);
        return sysUserMapper.updateById(sysUserPO);
    }

    @Override
    public int lock(SysUser sysUser) {
        var sysUserPO = userConverter.toPO(sysUser);
        return sysUserMapper.update(sysUserPO, Wrappers.<SysUserPO>lambdaUpdate()
                .eq(SysUserPO::getId, sysUser.getId())
                .set(SysUserPO::getAccessFailedCount, sysUser.getAccessFailedCount())
                );
    }

    @Override
    public int unLock(SysUser sysUser) {
        var sysUserPO = userConverter.toPO(sysUser);
        return sysUserMapper.update(sysUserPO, Wrappers.<SysUserPO>lambdaUpdate()
                .eq(SysUserPO::getId, sysUser.getId())
                .set(SysUserPO::getAccessFailedCount, sysUser.getAccessFailedCount())
        );
    }
}
