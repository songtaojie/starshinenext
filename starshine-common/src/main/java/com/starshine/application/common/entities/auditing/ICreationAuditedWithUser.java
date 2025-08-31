package com.starshine.application.common.entities.auditing;

/**
 * 创建审计接口
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 上午 10:33 周日
 */
public interface ICreationAuditedWithUser<TUser,TKey> extends ICreationAuditedWithKey<TKey> {
    /**
     * 获取创建者
     * @return 创建者
     */
    TUser getCreator();
}
