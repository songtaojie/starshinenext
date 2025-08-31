package com.starshine.application.infrastructure.persistence.po.base;

import com.starshine.application.common.entities.auditing.ICreationAuditedWithKey;
import lombok.Data;

import java.time.Instant;
/**
 * 创建审计PO
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-28 下午 周四
 */
@Data
public abstract class CreationAuditedPO implements ICreationAuditedWithKey<Long> {
    /**
     * 创建者Id
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    private Instant creationTime;
}
