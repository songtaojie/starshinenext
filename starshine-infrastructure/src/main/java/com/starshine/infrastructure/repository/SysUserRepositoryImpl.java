package com.starshine.infrastructure.repository;

import com.starshine.domain.SysUser;
import com.starshine.infrastructure.mapper.SysUserMapper;
import com.starshine.repository.ISysUserRepository;
import org.springframework.stereotype.Repository;

@Repository
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
}
