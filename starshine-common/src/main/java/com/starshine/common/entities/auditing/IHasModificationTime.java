package com.starshine.common.entities.auditing;

import java.time.Instant;

/**
 * 向类添加 ModificationTime 属性的标准接口。
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 上午 10:07 周日
 */
public interface IHasModificationTime {
    /**
     * 获取修改时间。
     * @return 修改时间
     */
    Instant getLastModificationTime();
}
