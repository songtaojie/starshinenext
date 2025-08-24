package com.starshine.common.entities;

import com.starshine.common.data.IHasExtraInfo;
import lombok.Data;

import java.util.Map;

/**
 * 聚合根基类
 */
@Data
public abstract class AggregateRootWithKey<TKey> extends BasicAggregateRootWithKey<TKey> implements IHasExtraInfo {
    /**
     * 额外的信息
     */
    private Map<String, String> extraInfo;
}
