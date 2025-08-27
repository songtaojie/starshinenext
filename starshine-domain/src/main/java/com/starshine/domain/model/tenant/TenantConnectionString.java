package com.starshine.domain.model.tenant;

import com.starshine.common.entities.Entity;
import com.starshine.common.entities.IMultiTenant;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 租户连接字符串
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-26 下午 周二
 */
@Data
@AllArgsConstructor
public class TenantConnectionString extends Entity implements IMultiTenant {

    /**
     * 租户Id
     */
    private Long tenantId;

    /**
     * 连接字符串名称
     */
    private String name;

    /**
     * 连接字符串值
     */
    private String value;

    @Override
    public Object[] getKeys() {
        return new Object[]{tenantId, name};
    }
}
