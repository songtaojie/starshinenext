package com.starshine.common.utils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 获取i18N资源文件
 * @author songtaojie
 */
public class MessageUtils {

    /**
     * 获取i18N资源文件
     * @author songtaojie
     * @version 1.0
     * @since 2025-08-23 下午 周六
     */
    public static String message(String code,Object... args){
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
