package com.starshine.common.entities.auditing;

/**
 * 创建者Id
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 上午 周六
 */
public interface IHasCreatorIdWithKey<TKey> {
    /**
     * 创建者Id
     * @return
     */
    TKey getCreatorId();
}
