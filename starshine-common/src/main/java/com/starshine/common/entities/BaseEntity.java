package com.starshine.common.entities;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 抽象实体类
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 上午 11:58 周日
 */
public abstract class BaseEntity implements IEntity, Serializable {

    @Override
    public String toString() {
        return String.format("[ENTITY: %s] Keys = %s",
                this.getClass().getSimpleName(),
                String.join(", ", Arrays.stream(getKeys())
                        .map(Object::toString).toArray(String[]::new)));
    }

    @Override
    public abstract Object[] getKeys();
}
