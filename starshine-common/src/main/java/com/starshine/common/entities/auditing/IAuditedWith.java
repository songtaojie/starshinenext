package com.starshine.common.entities.auditing;

/**
 * 可以实现此接口来向类添加标准审计属性。
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 上午 10:24 周日
 */
public interface IAuditedWith<TUser> extends ICreationAuditedWith<TUser>, IModificationAuditedWith<TUser>  {
}
