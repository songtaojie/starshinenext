package com.starshine.application.infrastructure.persistence.po.base;

import com.starshine.application.common.entities.auditing.ICreationAuditedWithUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建审计实体
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-28 下午 周四
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class CreationAuditedWithUserPO extends CreationAuditedPO implements ICreationAuditedWithUser<String,Long> {
    /**
     * 创建者
     */
    private String creator;
}
