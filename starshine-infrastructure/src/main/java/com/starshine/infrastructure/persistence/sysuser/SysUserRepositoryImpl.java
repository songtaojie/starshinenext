package com.starshine.infrastructure.persistence.sysuser;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.starshine.domain.SysUser;
import com.starshine.infrastructure.persistence.sysuser.mapper.SysUserMapper;
import com.starshine.repository.ISysUserRepository;
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
}
