package com.starshine.common.entities.auditing;

import lombok.Data;

import java.time.Instant;

/**
 * 带审计的聚合根
 * @param <TKey>
 */
@Data
public abstract class AuditedAggregateRootWithKey<TKey> extends CreationAuditedAggregateRootWithKey<TKey> implements IAuditedWith<TKey>{
    /**
     * 最后修改者Id
     */
    private TKey lastModifierId;

    /**
     * 最后修改时间
     */
    private Instant lastModificationTime;
}
