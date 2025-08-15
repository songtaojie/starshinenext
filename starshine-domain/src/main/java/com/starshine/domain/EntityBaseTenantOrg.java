package com.starshine.domain;

import lombok.Data;

/**
 * 租户机构实体基类（数据权限）
 */
@Data
public class EntityBaseTenantOrg extends EntityBaseOrg{
    private Long tenantId;
}
