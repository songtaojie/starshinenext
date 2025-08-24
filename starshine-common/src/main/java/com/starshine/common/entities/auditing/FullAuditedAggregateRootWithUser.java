package com.starshine.common.entities.auditing;

/**
 * 继承此抽象类，实现IFullAuditedWithUser接口
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 上午 周六
 */
public abstract class FullAuditedAggregateRootWithUser<TUser,TKey> extends FullAuditedAggregateRootWithKey<TKey> implements IFullAuditedWithUser<TUser,TKey> {
}
