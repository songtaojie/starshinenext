package com.starshine.core.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 字符串工具类
 * @author starshine
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    /**
     * 空字符串
     */
    public static final String EMPTY = "";

    /**
     * 空格
     */
    public static final String SPACE = " ";

    /**
     * 下划线
     */
    public static final char SEPARATOR = '_';

    /**
     * 星号
     */
    public static final char ASTERISK = '*';

    /**
     * 判断一个Collection是否为空，包含List，Set，Queue
     * @param collection 要判断的Colection
     * @return true 为空；false 非空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return isNull(collection) || collection.isEmpty();
    }

    /**
     * 判断一个map是否为空
     * @param map 要判断的map
     * @return true 为空；false 非空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    /**
     *判断一个字符串是否为空
     * @param str 要判断的字符串
     * @return true 为空；false 非空
     */
    public static boolean isEmpty(String str){
        return isNull(str) || EMPTY.equals(str.trim());
    }
    /**
     * 判断一个对象是否为空
     * @param object
     * @return true 非空，false 空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }
}
