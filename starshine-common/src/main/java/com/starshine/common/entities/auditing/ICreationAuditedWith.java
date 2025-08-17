package com.starshine.common.entities.auditing;

/**
 * 创建审计接口
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 上午 10:33 周日
 */
public interface ICreationAuditedWith<TCreator> extends ICreationAudited {
    /**
     * 创建者ID
     * @return 创建者ID
     */
    TCreator getCreator();
}
