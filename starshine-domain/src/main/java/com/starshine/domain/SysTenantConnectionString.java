package com.starshine.domain;

import lombok.Data;

/**
 * 租户连接字符串
 * @author starshine
 */
@Data
public class SysTenantConnectionString extends EntityBaseTenantId{

    /**
     * 连接字符串名称
     */
    private String name;

    /**
     * 连接字符串值
     */
    private String value;
}
