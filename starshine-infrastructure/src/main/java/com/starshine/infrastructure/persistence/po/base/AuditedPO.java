package com.starshine.infrastructure.persistence.po.base;

import com.starshine.application.common.entities.auditing.IAuditedWithKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

/**
 * 审计PO
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-28 下午 周四
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AuditedPO extends CreationAuditedPO implements IAuditedWithKey<Long> {
    /**
     * 最后修改者Id
     */
    private Long lastModifierId;

    /**
     * 最后修改时间
     */
    private Instant lastModificationTime;
}
