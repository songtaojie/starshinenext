package com.starshine.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starshine.infrastructure.persistence.po.SysConfigPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统配置
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-30 下午 周六
 */
@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfigPO> {
}
