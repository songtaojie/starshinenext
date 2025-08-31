package com.starshine.application.domain.model.config;

import com.starshine.application.common.entities.auditing.BaseAuditedAggregateRoot;
import lombok.Data;

/**
 * 系统配置
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-30 下午 周六
 */
@Data
public class SysConfig extends BaseAuditedAggregateRoot {
    /**
     * 配置名称
     */
    private String name;

    /**
     * 配置编码
     */
    private String code;

    /**
     * 配置值
     */
    private String value;

    /**
     * 配置类型
     * 1:系统配置 2:租户配置
     */
    private String configType;

    /**
     * 配置分组
     */
    private String groupCode;

    /**
     * 配置描述
     */
    private String description;

    /**
     * 排序
     */
    private int sort;

    /**
     * 是否启用
     */
    private boolean active;
}
