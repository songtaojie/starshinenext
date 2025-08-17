package com.starshine.common.entities;

/**
 * 实体接口，包含键值
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 上午 11:30 周日
 * @param <TKey> 实体主键的类型
 */
public interface IEntityWithKey<TKey> extends IEntity {

    /**
     * 获取实体的键值
     * @return 实体的键值
     */
    TKey getId();
}
