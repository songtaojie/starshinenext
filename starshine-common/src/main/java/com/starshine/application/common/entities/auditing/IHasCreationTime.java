package com.starshine.application.common.entities.auditing;

import java.time.Instant;

/**
 * 添加 CreationTime 属性的标准接口。
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 上午 10:03 周日
 */
public interface IHasCreationTime {

    /**
     * 创建时间
     * @return
     */
    Instant getCreationTime();
}
