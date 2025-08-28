package com.starshine.common.entities.auditing;

import com.starshine.common.entities.BaseAggregateRootWithKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

/**
 * 创建审计聚合根
 * @param <TKey>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseCreationAuditedAggregateRootWithKey<TKey> extends BaseAggregateRootWithKey<TKey> implements ICreationAuditedWithKey<TKey> {
    /**
     * 创建者Id
     */
    private TKey creatorId;

    /**
     * 创建时间
     */
    private Instant creationTime;
}
