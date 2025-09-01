package com.starshine.common.entities.auditing;

/**
 * @author starshine
 * @version 1.0
 * @since 2025-08-16 下午 21:48 周六
 */
public interface IHasEntityVersion {
    /**
     * 每当实体发生变化时，版本值就会增加。
     */
     int getEntityVersion();
}
