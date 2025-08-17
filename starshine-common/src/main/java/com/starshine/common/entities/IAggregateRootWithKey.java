package com.starshine.common.entities;

/**
 *定义具有“Id”属性的单个主键的聚合根。
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 下午 18:41 周日
 * @param <TKey> 实体主键的类型
 */
public interface IAggregateRootWithKey<TKey> extends IEntityWithKey<TKey>, IAggregateRoot {
}
