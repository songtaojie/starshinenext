package com.starshine.application.common.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 聚合根基类
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-28 下午 周四
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseAggregateRootWithKey<TKey>  extends BaseEntityWithKey<TKey> implements IAggregateRootWithKey<TKey>  {
}
