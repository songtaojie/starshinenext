package com.starshine.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtils implements BeanFactoryPostProcessor, ApplicationContextAware {

    private static ApplicationContext applicationContext;
    /**
     * spring上下文环境
     */
    private static ConfigurableListableBeanFactory beanFactory;

    /**
     * 获取对象
     * @param name
     * @param <T>
     * @return 返回一个衣索非对象名字注册的对象实例
     * @throws BeansException
     */
    public static <T> T getBean(String name) throws BeansException {
        return (T) beanFactory.getBean(name);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtils.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }
}
