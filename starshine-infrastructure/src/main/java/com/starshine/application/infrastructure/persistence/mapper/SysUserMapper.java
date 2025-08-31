package com.starshine.application.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starshine.application.infrastructure.persistence.po.SysUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户mapper
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserPO> {
}
