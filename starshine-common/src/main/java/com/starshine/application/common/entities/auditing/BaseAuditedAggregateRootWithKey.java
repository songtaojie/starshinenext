package com.starshine.application.common.entities.auditing;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

/**
 * 带审计的聚合根
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-28 下午 周四
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseAuditedAggregateRootWithKey<TKey> extends BaseCreationAuditedAggregateRootWithKey<TKey> implements IAuditedWithKey<TKey> {
    /**
     * 最后修改者Id
     */
    private TKey lastModifierId;

    /**
     * 最后修改时间
     */
    private Instant lastModificationTime;
}
