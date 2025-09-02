package com.starshine.domain.config;

/**
 * 系统配置仓储
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-30 下午 周六
 */
public interface ISysConfigRepository {
    /**
     * 根据编码查询系统配置
     * @param code
     * @return
     */
    SysConfig findByCode(String code);
}
