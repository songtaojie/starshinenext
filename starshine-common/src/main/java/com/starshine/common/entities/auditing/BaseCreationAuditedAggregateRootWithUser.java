package com.starshine.common.entities.auditing;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建审计聚合根
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 上午 周六
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseCreationAuditedAggregateRootWithUser<TUser,TKey> extends BaseCreationAuditedAggregateRootWithKey<TKey> implements ICreationAuditedWithUser<TUser,TKey> {
    /**
     * 创建者
     */
    private TUser creator;
}
