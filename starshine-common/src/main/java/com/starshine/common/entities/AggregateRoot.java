package com.starshine.common.entities;

import com.starshine.common.data.IHasExtraInfo;
import lombok.Data;

import java.util.Map;

/**
 * 聚合根基类
 */
@Data
public abstract class AggregateRoot extends BasicAggregateRoot implements IHasExtraInfo {
    private Map<String, String> extraInfo;
}
