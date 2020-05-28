package com.scg.test.springtest.lesson_01;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WinCondition implements Condition {
    /**
     * 条件注入 Windows
     * @param context   判断条件可以使用的上下文
     * @param metadata  注解信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Environment environment = context.getEnvironment();
        String os_name = environment.getProperty("os.name");
        if(os_name.contains("Windows"))
            return true;
        return false;
    }
}
