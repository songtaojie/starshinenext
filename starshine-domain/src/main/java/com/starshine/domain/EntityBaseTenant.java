package com.starshine.domain;

import lombok.Data;

/**
 * 多租户
 * @author: starshine
 */
@Data
public class EntityBaseTenant extends EntityBase{
    private Long tenantId;
}
