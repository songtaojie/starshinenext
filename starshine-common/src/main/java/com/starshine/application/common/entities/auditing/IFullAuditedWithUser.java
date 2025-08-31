package com.starshine.application.common.entities.auditing;

/**
 * 创建者、修改者、删除者、删除时间、创建时间、修改时间
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 上午 11:21 周日
 */
public interface IFullAuditedWithUser<TUser,TKey> extends IAuditedWithUser<TUser,TKey>, IDeletionAuditedWithUser<TUser,TKey> {
}

