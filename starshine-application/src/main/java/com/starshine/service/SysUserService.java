package com.starshine.service;

import com.starshine.domain.sysuser.SysUser;
import com.starshine.repository.ISysUserRepository;
import org.springframework.stereotype.Service;

/**
 * 系统用户服务实现类
 * @author: starshine
 * @version: 1.0
 * @since: 2025/8/16  下午 20:19  周六
 * @Description: 系统用户服务实现类
 */
@Service
public class SysUserService {

    private final ISysUserRepository sysUserRepository;
    /**
     * 构造函数
     * @param sysUserRepository 系统用户仓库
     */
    public SysUserService(ISysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return 用户
     */
    public SysUser findById(Long id) {
        return sysUserRepository.findById(id);
    }

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    public SysUser findByUsername(String username) {
        return sysUserRepository.findByUsername(username);
    }
}
