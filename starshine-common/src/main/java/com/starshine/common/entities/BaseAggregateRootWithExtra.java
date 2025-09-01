package com.starshine.common.entities;

import com.starshine.common.data.IHasExtraInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 聚合根基类
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-28 下午 周四
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseAggregateRootWithExtra extends BaseAggregateRoot implements IHasExtraInfo {
    /**
     * 额外的信息
     */
    private Map<String, String> extraInfo;
}
