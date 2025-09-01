package com.starshine.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.starshine.infrastructure.persistence.po.base.AuditedPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统配置PO
 */
@TableName("sys_config")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysConfigPO extends AuditedPO {
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
