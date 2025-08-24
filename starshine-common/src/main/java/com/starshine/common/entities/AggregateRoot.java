package com.starshine.common.entities;

import com.starshine.common.data.IHasExtraInfo;
import lombok.Data;

import java.util.Map;

/**
 * 聚合根基类
 */
@Data
public abstract class AggregateRoot extends BasicAggregateRoot implements IHasExtraInfo {
    /**
     * 额外的信息
     */
    private Map<String, String> extraInfo;
}
