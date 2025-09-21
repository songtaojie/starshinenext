package com.starshine.infrastructure.persistence.po.base;

import com.starshine.common.entities.BaseEntityWithKey;
import com.starshine.common.entities.IEntityWithKey;
import com.starshine.common.entities.auditing.ICreationAuditedWithKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
/**
 * 创建审计PO
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-28 下午 周四
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class CreationAuditedPO extends BaseEntityWithKey<Long> implements ICreationAuditedWithKey<Long> {
    /**
     * 创建者Id
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    private Instant creationTime;
}
