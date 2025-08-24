package com.starshine.common.entities.auditing;

/**
 * 带用户审计的聚合根
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 上午 周六
 */
public abstract class AuditedAggregateRootWithUser<TUser,TKey> extends AuditedAggregateRootWithKey<TKey> implements IAuditedWithUser<TUser,TKey> {
    /**
     * 最后修改者
     */
    private TUser lastModifier;
}
