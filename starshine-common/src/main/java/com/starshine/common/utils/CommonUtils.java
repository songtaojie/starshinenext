package com.starshine.common.utils;

import java.util.UUID;

/**
 * 通用工具类
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-30 下午 周六
 */
public class CommonUtils {

    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID()
    {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUpperCaseUUID()
    {
        return randomUUID().toUpperCase();
    }

    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String simpleUpperCaseUUID()
    {
        return simpleUUID().toUpperCase();
    }

}
