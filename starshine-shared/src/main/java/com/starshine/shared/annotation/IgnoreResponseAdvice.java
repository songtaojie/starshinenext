package com.starshine.shared.annotation;

import java.lang.annotation.*;

/**
 * 忽略统一返回结果
 * @author songtaojie
 * @since 2025-09-17 周三
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreResponseAdvice {
}
