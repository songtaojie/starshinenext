package com.starshine.application.common.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 下午 16:01 周日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEntityWithKey<TKey> extends BaseEntity implements IEntityWithKey<TKey>, Serializable {
    protected TKey id;

    protected BaseEntityWithKey() {
    }

    protected BaseEntityWithKey(TKey id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "[ENTITY: " + getClass().getSimpleName() + "] Id = " + id;
    }

    @Override
    public Object[] getKeys() {
        return new Object[]{ id };
    }
}
