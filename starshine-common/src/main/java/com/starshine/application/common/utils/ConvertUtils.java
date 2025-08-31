package com.starshine.application.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 转换工具类
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-30 下午 周六
 */
@Slf4j
public class ConvertUtils {
    /**
     * 对象转换成字符串
     * 如果值为null，或者转换失败，则返回默认值<br>
     * @param value 待转换对象
     * @return 转换后的字符串
     */
    public static String toString(Object value, String defaultValue) {
        if (value == null) return defaultValue;
        if (value instanceof String)
            return (String) value;
        return value.toString();
    }

    /**
     * 获取对象字符串值
     * @param value 待转换对象
     * @return 获取对象字符串值
     */
    public static String toString(Object value) {
        return toString(value, null);
    }

    // 缓存 valueOf 方法以提高性能
    private static final Map<String, Method> VALUE_OF_METHOD_CACHE = new HashMap<>();

    /**
     * 将 value 转换为指定的目标类型 targetType
     *
     * @param value       要转换的值（通常为 String）
     * @param targetType  目标类型 Class
     * @param <T>         目标类型
     * @return 转换后的对象，失败返回 null
     */
    @SuppressWarnings("unchecked")
    public static <T> T changeType(Object value, Class<T> targetType) {
        if (value == null) {
            return null;
        }

        // 如果 value 本身就是目标类型或其父类，直接强转
        if (targetType.isInstance(value)) {
            return (T) value;
        }

        // 将 value 转为字符串（核心输入通常是 String）
        String strValue = value.toString().trim();
        if (strValue.isEmpty()) {
            return null;
        }

        try {
            // 常见类型的专用转换
            if (targetType == String.class) {
                return (T) strValue;
            } else if (targetType == Integer.class || targetType == int.class) {
                return (T) Integer.valueOf(strValue);
            } else if (targetType == Long.class || targetType == long.class) {
                return (T) Long.valueOf(strValue);
            } else if (targetType == Double.class || targetType == double.class) {
                return (T) Double.valueOf(strValue);
            } else if (targetType == Float.class || targetType == float.class) {
                return (T) Float.valueOf(strValue);
            } else if (targetType == Boolean.class || targetType == boolean.class) {
                return (T) Boolean.valueOf(strValue);
            } else if (targetType == Short.class || targetType == short.class) {
                return (T) Short.valueOf(strValue);
            } else if (targetType == Byte.class || targetType == byte.class) {
                return (T) Byte.valueOf(strValue);
            } else if (targetType == Character.class || targetType == char.class) {
                return strValue.length() == 1 ? (T) Character.valueOf(strValue.charAt(0)) : null;
            } else if (targetType == BigDecimal.class) {
                return (T) new BigDecimal(strValue);
            } else if (targetType == BigInteger.class) {
                return (T) new BigInteger(strValue);
            } else if (targetType == LocalDate.class) {
                return (T) LocalDate.parse(strValue);
            } else if (targetType == LocalDateTime.class) {
                return (T) LocalDateTime.parse(strValue, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }

            // 其他类型：尝试调用 valueOf(String)
            Method valueOfMethod = getValueOfMethod(targetType);
            if (valueOfMethod != null) {
                return (T) valueOfMethod.invoke(null, strValue);
            }

            // 尝试构造函数 new Type(String)
            try {
                return targetType.getConstructor(String.class).newInstance(strValue);
            } catch (Exception ignored) {
                log.warn("类型转换失败，无法调用 {} 的 valueOf(String) 方法", targetType.getSimpleName());
            }
        } catch (Exception e) {
            log.warn("类型转换失败: '{}' -> {}", value, targetType.getSimpleName(), e);
        }
        return null;
    }

    /**
     * 获取类的 valueOf(String) 方法（缓存优化）
     */
    private static Method getValueOfMethod(Class<?> clazz) {
        String key = clazz.getName();
        return VALUE_OF_METHOD_CACHE.computeIfAbsent(key, k -> {
            try {
                return clazz.getDeclaredMethod("valueOf", String.class);
            } catch (NoSuchMethodException e) {
                return null;
            }
        });
    }
}
