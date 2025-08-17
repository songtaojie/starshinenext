package com.starshine.repository;

import com.starshine.domain.SysUser;
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
}
