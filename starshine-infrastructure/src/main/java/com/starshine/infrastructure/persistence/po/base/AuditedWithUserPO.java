package com.starshine.infrastructure.persistence.po.base;

import com.starshine.common.entities.auditing.ICreationAuditedWithUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 审计用户PO
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-28 下午 周四
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AuditedWithUserPO extends AuditedPO implements ICreationAuditedWithUser<String,Long> {
    /**
     * 最后修改者
     */
    private String lastModifier;
}
