package com.scg.test.springtest.lesson_02;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Jeep implements ApplicationContextAware {
    public ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //获取IOC容器，做一些自定义操作
        System.out.println("获取ApplicationContextAware实例……");
        this.applicationContext = applicationContext;
    }
}
