package com.scg.test.springtest.lesson_01;

import org.springframework.beans.factory.FactoryBean;

public class FactoryBeanDemo implements FactoryBean<Coffee> {
    @Override
    public Coffee getObject() throws Exception {
        return new Coffee();
    }

    @Override
    public Class<?> getObjectType() {
        return Coffee.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
