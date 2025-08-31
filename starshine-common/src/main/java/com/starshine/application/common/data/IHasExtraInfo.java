package com.starshine.application.common.data;

import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * 描述实体的额外信息
 */
public interface IHasExtraInfo {
    /**
     * 获取额外信息的映射表
     *
     * @return 包含额外信息的键值对集合
     */
    @Nullable
    Map<String, String> getExtraInfo();

    /**
     * 设置整个额外信息映射
     *
     * @param extraInfo 新的额外信息映射
     */
    void setExtraInfo(Map<String, String> extraInfo);
}
