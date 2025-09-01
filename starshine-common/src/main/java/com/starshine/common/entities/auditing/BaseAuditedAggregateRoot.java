package com.starshine.common.entities.auditing;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 审计聚合根
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-28 下午 周四
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseAuditedAggregateRoot extends BaseAuditedAggregateRootWithKey<Long> {
}
