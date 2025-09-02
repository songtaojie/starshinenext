package com.starshine.infrastructure.security;

import com.starshine.domain.user.User;
import com.starshine.domain.enums.UserStatus;
import com.starshine.domain.user.IUserRepository;
import com.starshine.shared.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义用户详情服务
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 下午 周六
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IUserRepository sysUserRepository;

    public UserDetailsServiceImpl(IUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws ServiceException {
        User sysUser = sysUserRepository.findByUsername(username);
        if (sysUser == null) {
            log.info("登录用户：{}不存在", username);
            throw new UsernameNotFoundException("登录用户：" + username + "不存在");
        }
        if(sysUser.isDeleted()) {
            log.info("登录用户：{}已删除", username);
            throw new UsernameNotFoundException("登录用户：" + username + "已删除");
        }
        if(!sysUser.isLockoutEnabled()) {
            log.info("登录用户：{}已锁定", username);
            throw new DisabledException("登录用户：" + username + "已锁定");
        }
        if(UserStatus.DISABLED.getCode().equals(sysUser.getStatus())) {
            log.info("登录用户：{}已禁用", username);
            throw new DisabledException("登录用户：" + username + "已禁用");
        }
        return new CustomUserDetails(sysUser);
    }

}
