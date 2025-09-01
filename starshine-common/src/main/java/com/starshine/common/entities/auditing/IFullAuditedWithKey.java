package com.starshine.common.entities.auditing;

/**
 * FullAuditedWithKey
 * @param <TKey>
 */
public interface IFullAuditedWithKey<TKey> extends IAuditedWithKey<TKey>, IDeletionAuditedWithKey<TKey>{
}
