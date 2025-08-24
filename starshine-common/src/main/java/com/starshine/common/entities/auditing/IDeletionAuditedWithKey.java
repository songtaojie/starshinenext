package com.starshine.common.entities.auditing;

/**
 * 删除审计接口
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 上午 周六
 */
public interface IDeletionAuditedWithKey<TKey> extends IHasDeletionTime,ISoftDelete  {
    /**
     * 删除者Id
     */
    TKey getDeleterId();
}
