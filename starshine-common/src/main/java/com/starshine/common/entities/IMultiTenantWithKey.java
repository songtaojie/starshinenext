package com.starshine.common.entities;

/**
 * 多租户接口
 * @param <TKey>
 */
public interface IMultiTenantWithKey<TKey> {
    /**
     * 获取租户Id
     * @return
     */
    TKey getTenantId();
}
