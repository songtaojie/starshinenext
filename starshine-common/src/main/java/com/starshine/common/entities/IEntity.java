package com.starshine.common.entities;
/**
 * 定义一个实体。其主键可以不是“Id”，也可以是复合主键。
 * 尽可能使用 {@link IEntityWithKey}，以便更好地与存储库和框架中的其他结构集成。
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 上午 11:26 周日
 */
public interface IEntity
{
    /**
     * 获取实体的主键。
     * @return 返回此实体的有序键数组。
     */
    Object[] GetKeys();
}
