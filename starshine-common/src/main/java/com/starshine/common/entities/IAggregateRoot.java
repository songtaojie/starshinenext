package com.starshine.common.entities;

/**
 * 定义聚合根。其主键可以不是“Id”，也可以是复合主键。
 * 尽可能使用 {@link IAggregateRootWithKey}，以便更好地与存储库和框架中的其他结构集成。
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 下午 15:59 周日
 */
public interface IAggregateRoot extends IEntity{
}
