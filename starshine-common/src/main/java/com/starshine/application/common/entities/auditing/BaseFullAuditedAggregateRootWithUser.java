package com.starshine.application.common.entities.auditing;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 继承此抽象类，实现IFullAuditedWithUser接口
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-23 上午 周六
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseFullAuditedAggregateRootWithUser<TUser,TKey> extends BaseFullAuditedAggregateRootWithKey<TKey> implements IFullAuditedWithUser<TUser,TKey> {

    /**
     * 删除者
     */
    private TUser deleter;
}
