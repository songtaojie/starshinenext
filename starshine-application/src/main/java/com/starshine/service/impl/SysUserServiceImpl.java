package com.starshine.service.impl;

import com.starshine.domain.SysUser;
import com.starshine.repository.ISysUserRepository;
import com.starshine.service.ISysUserService;

/**
 * 系统用户服务实现类
 * @author: starshine
 * @version: 1.0
 * @since: 2025/8/16  下午 20:19  周六
 * @Description: 系统用户服务实现类
 */
public class SysUserServiceImpl implements ISysUserService {

    private final ISysUserRepository sysUserRepository;
    public SysUserServiceImpl(ISysUserRepository sysUserRepository){
        this.sysUserRepository = sysUserRepository;
    }
    /**
     * 根据id查询用户
     * @param id 用户id
     * @return 用户
     */
    @Override
    public SysUser findById(Long id) {
        return sysUserRepository.findById( id);
    }
}
