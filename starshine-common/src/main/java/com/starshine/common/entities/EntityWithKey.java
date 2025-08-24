package com.starshine.common.entities;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 下午 16:01 周日
 */
@Data
public abstract class EntityWithKey<TKey> extends Entity implements IEntityWithKey<TKey>, Serializable {
    protected TKey id;

    protected EntityWithKey() {
    }

    protected EntityWithKey(TKey id) {
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
