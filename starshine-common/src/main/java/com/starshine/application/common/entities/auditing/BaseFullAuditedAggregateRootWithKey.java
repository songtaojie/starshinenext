package com.starshine.application.common.entities.auditing;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

/**
 * 创建者、修改者、删除者、审计信息聚合根
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-28 下午 周四
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseFullAuditedAggregateRootWithKey<TKey> extends BaseAuditedAggregateRootWithKey<TKey> implements IFullAuditedWithKey<TKey> {
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
