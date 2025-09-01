package com.starshine.common.entities.auditing;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 带用户审计的聚合根
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 上午 周六
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseAuditedAggregateRootWithUser<TUser,TKey> extends BaseAuditedAggregateRootWithKey<TKey> implements IAuditedWithUser<TUser,TKey> {
    /**
     * 最后修改者
     */
    private TUser lastModifier;
}
