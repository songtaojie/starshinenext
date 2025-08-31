package com.starshine.application.common.entities.auditing;

/**
 * 创建审计实体接口
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 上午 周六
 */
public interface ICreationAuditedWithKey<TKey> extends IHasCreationTime,IHasCreatorIdWithKey<TKey> {
}
