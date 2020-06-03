package com.scg.test.springtest.lesson_02;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class Plane implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //前置处理器
        System.out.println("postProcessBeforeInitialization……"+bean+"……"+beanName);
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //后置处理器
        System.out.println("postProcessAfterInitialization……"+bean+"……"+beanName);
        return null;
    }
}
