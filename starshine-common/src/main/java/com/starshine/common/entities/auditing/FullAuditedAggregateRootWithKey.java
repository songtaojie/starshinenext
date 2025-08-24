package com.starshine.common.entities.auditing;

import lombok.Data;
import java.time.Instant;

/**
 * 创建者、修改者、删除者、审计信息聚合根
 * @param <TKey>
 */
@Data
public abstract class FullAuditedAggregateRootWithKey<TKey> extends AuditedAggregateRootWithKey<TKey> implements IFullAuditedWithKey<TKey> {
    /**
     * 删除者
     */
    private TKey deleterId;

    /**
     * 是否删除
     */
    private boolean deleted;

    /**
     * 删除时间
     */
    private Instant deletionTime;
}
