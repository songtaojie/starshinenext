package com.starshine.common.entities.auditing;

import lombok.Data;

/**
 * 审计聚合根
 * @author
 */
@Data
public abstract class AuditedAggregateRoot extends AuditedAggregateRootWithKey<Long> {
}
