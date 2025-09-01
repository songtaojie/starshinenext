package com.starshine.infrastructure.persistence.po.base;

import com.starshine.application.common.entities.auditing.IFullAuditedWithKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
/**
 * 创建者、修改者、删除者、删除时间、创建时间、修改时间
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-28 下午 周四
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class FullAuditedPO extends AuditedPO implements IFullAuditedWithKey<Long> {
    /**
     * 删除者
     */
    private Long deleterId;

    /**
     * 是否删除
     */
    private boolean deleted;

    /**
     * 删除时间
     */
    private Instant deletionTime;
}
