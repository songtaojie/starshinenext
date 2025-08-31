package com.starshine.application.common.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Bean 工具类
 * @author songtaojie
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
    /**
     * Bean方法名属性名开始的下标
     */
    private static final int BEAN_METHOD_PROP_INDEX = 3;

    /**
     * 匹配getter方法的正则
     */
    private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w+)");

    /**
     * 匹配setter方法的正则
     */
    private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w+)");

    /**
     * Bean属性复制工具方法
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyBeanProperties(Object source, Object target) {
        try {
            copyProperties(source, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象的setter方法
     * @param obj 对象
     * @return 对象的setter方法列表
     */
    public static List<Method> getSetterMethods(Object obj) {
        List<Method> setterMethods = new ArrayList<>();
        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();

        // 查找setter方法
        for (Method method : methods) {
            Matcher matcher = SET_PATTERN.matcher(method.getName());
            if (matcher.matches() && method.getParameterTypes().length == 1) {
                setterMethods.add(method);
            }
        }
        return setterMethods;
    }

    /**
     * 获取对象的gettter方法
     * @param obj 对象
     * @return 对象的getter方法列表
     */
    public static List<Method> getGetterMethods(Object obj) {
        List<Method> getterMethods = new ArrayList<>();
        Method[] methods = obj.getClass().getMethods();
        for (Method method : methods) {
            Matcher matcher = GET_PATTERN.matcher(method.getName());
            if (matcher.matches() && method.getParameterTypes().length == BEAN_METHOD_PROP_INDEX) {
                getterMethods.add(method);
            }
        }
        return getterMethods;
    }

    /**
     * 检查Bean方法名中的属性名是否相等 <br>
     * 如getName()和setName()属性名一样的，getName()和setAge()的属性名不一样。
     * @param m1 方法名1
     * @param m2 方法名2
     * @return 属性名一样返回true，否则返回false
     */
    public static boolean isMethodPropEquals(String m1, String m2) {
        return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
    }
}
