package com.starshine.application.common.entities.auditing;

/**
 * 带主键的修改审计接口
 *
 * @param <TKey> 主键类型
 */
public interface IModificationAuditedWithKey<TKey> extends IHasModificationTime {

    /**
     * 最后修改人ID
     *
     * @return 最后修改人ID
     */
    TKey getLastModifierId();
}
