package com.starshine.application.infrastructure.persistence.po.base;

import com.starshine.application.common.entities.auditing.ICreationAuditedWithUser;
import lombok.Data;

/**
 * 审计用户PO
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-28 下午 周四
 */
@Data
public abstract class AuditedWithUserPO extends AuditedPO implements ICreationAuditedWithUser<String,Long> {
    /**
     * 最后修改者
     */
    private String lastModifier;
}
