package com.scg.test.springtest.lesson_02;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Dog implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("哈士奇生病了-----状态不佳，改天拆家！！！！");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("哈士奇登场-------当当当当！！！！");
    }
}
