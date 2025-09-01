package com.starshine.infrastructure.persistence.converter;

import com.starshine.domain.model.config.SysConfig;
import com.starshine.infrastructure.persistence.po.SysConfigPO;
import org.mapstruct.Mapper;

/**
 * 配置转换器
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-30 下午 周六
 */
@Mapper(componentModel = "spring")
public interface ConfigConverter {
    /**
     * po 转 domain
     * @param po po
     * @return domain
     */
    SysConfig toDomain(SysConfigPO po);

    /**
     * domain 转 po
     * @param domain domain
     * @return po
     */
    SysConfigPO toPO(SysConfig domain);
}
