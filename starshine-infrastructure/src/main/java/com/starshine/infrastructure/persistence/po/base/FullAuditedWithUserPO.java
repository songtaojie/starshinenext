package com.starshine.infrastructure.persistence.po.base;

import com.starshine.application.common.entities.auditing.IFullAuditedWithUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 创建者、修改者、删除者、创建时间、修改时间、删除时间
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-28 下午 周四
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class FullAuditedWithUserPO extends FullAuditedPO implements IFullAuditedWithUser<String,Long> {
    /**
     * 删除者
     */
    private String deleter;
}
