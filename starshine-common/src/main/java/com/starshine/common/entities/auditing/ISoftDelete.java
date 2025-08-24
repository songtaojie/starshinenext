package com.starshine.common.entities.auditing;

/**
 * 用于规范软删除实体。软删除实体实际上并没有被删除，
 * 在数据库中标记为 Deleted = true，但无法正常检索到应用程序。
 * @author starshine
 * @version 1.0
 * @since 2025-08-17 上午 10:08 周日
 */
public interface ISoftDelete {

    /**
     * 获取实体是否被删除
     * @return 实体是否被删除
     */
    boolean isDeleted();
}
