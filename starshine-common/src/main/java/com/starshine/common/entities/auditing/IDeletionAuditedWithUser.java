package com.starshine.common.entities.auditing;

/**
 * 删除审计
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 上午 10:59 周日
 */
public interface IDeletionAuditedWithUser<TUser, TKey> extends IDeletionAuditedWithKey<TKey> {
    /**
     * 删除者
     * @return 删除者
     */
    TUser getDeleter();
}
