package com.starshine.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.starshine.application.common.cache.RedisCache;
import com.starshine.application.common.utils.StringUtils;
import com.starshine.application.domain.model.config.SysConfig;
import com.starshine.application.domain.repository.ISysConfigRepository;
import com.starshine.infrastructure.persistence.converter.ConfigConverter;
import com.starshine.infrastructure.persistence.mapper.SysConfigMapper;
import com.starshine.infrastructure.persistence.po.SysConfigPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 系统参数
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-30 下午 周六
 */
@Repository
@RequiredArgsConstructor
public class SysConfigRepositoryImpl implements ISysConfigRepository {
    private final SysConfigMapper sysConfigMapper;
    private final RedisCache redisCache;
    private final ConfigConverter configConverter;

    /**
     * 根据参数键名查询参数值
     *
     * @param code 参数键
     * @return 参数值
     */
    public SysConfig findByCode(String code) {
        if(StringUtils.isEmpty( code)){
            return null;
        }
        SysConfigPO sysConfigPO = sysConfigMapper.selectOne(Wrappers.<SysConfigPO>lambdaQuery()
                .eq(SysConfigPO::getCode, code));
        return configConverter.toDomain(sysConfigPO);
    }

}
