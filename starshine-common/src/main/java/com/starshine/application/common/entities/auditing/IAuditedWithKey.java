package com.starshine.application.common.entities.auditing;

/**
 * 继承此接口，表示实体有审计信息
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 上午 周六
 */
public interface IAuditedWithKey<TKey>  extends ICreationAuditedWithKey<TKey>,IModificationAuditedWithKey<TKey>{
}
