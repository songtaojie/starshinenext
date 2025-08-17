package com.starshine.common.entities.auditing;

/**
 * 删除审计
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 上午 10:57 周日
 */
public interface IDeletionAudited extends IHasDeletionTime {
    /**
     * 删除者Id
     * @return 删除者Id
     */
    Long getDeleterId();
}
