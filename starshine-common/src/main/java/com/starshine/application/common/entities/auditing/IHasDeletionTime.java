package com.starshine.application.common.entities.auditing;

import java.time.Instant;

/**
 * 删除时间戳
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 上午 10:56 周日
 */
public interface IHasDeletionTime extends ISoftDelete{
    /**
     * 删除时间戳
     * @return
     */
    Instant getDeletionTime();
}
