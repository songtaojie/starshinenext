package com.starshine.common.entities;

/**
 * 基础聚合根
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 下午 23:01 周日
 */
public abstract class BasicAggregateRootWithKey<TKey> extends EntityWithKey<TKey> implements IAggregateRootWithKey<TKey> {
}
