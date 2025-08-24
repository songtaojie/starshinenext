package com.starshine.common.entities.auditing;

import lombok.Data;

/**
 * 创建审计聚合根
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 上午 周六
 */
@Data
public abstract class CreationAuditedAggregateRootWithUser<TUser,TKey> extends CreationAuditedAggregateRootWithKey<TKey> implements ICreationAuditedWithUser<TUser,TKey> {
    /**
     * 创建者
     */
    private TUser creator;
}
