package com.starshine.application.common.entities.auditing;

/**
 * 修改着审计
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 上午 10:22 周日
 */
public interface IModificationAuditedWithUser<TUser,TKey> extends IModificationAuditedWithKey<TKey> {
    /**
     * 最后修改者ID
     * @return 最后修改者ID
     */
    TUser getLastModifier();
}
