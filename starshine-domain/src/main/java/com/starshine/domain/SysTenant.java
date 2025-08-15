package com.starshine.domain;

import lombok.Data;

/**
 * 租户
 * @author starshine
 */
@Data
public class SysTenant extends EntityBase {

    /**
     * 租户名称
     */
    private String name;

    /**
     * 租户名称
     */
    private String normalizedName;
}
