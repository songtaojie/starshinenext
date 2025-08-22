package com.starshine.common.entities.auditing;

import com.starshine.common.entities.AggregateRootWithKey;
import lombok.Data;

import java.time.Instant;

/**
 * 创建审计聚合根
 * @param <TKey>
 */
@Data
public abstract class CreationAuditedAggregateRootWithKey<TKey> extends AggregateRootWithKey<TKey> implements ICreationAudited {
    /**
     * 创建者Id
     */
    private  TKey creatorId;

    /**
     * 创建时间
     */
    private Instant creationTime;
}
