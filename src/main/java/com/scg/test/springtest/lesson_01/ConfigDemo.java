package com.scg.test.springtest.lesson_01;

import org.springframework.context.annotation.*;

@Configuration
@Import(value = {Coffee.class,ImportSelectorDemo.class,ImportBeanDefinitionRegistrarDemo.class})//将对象注入IOC容器，id为类全路径名字
public class ConfigDemo {
    @Bean
    public Person tom(){
        System.out.println("tom加载到IOC容器里了");
        return new Person("tom","12");
    }
    @Scope("prototype")//多实例模式
    @Bean
    public Person jerry(){
        System.out.println("jerry加载到IOC容器里了");
        return new Person("jerry","10");
    }
    @Lazy//懒加载，第一次调用时才实例化
    @Bean
    public Person jessy(){
        System.out.println("jessy加载到IOC容器里了,为什么用到我的时候才想起我！");
        return new Person("jessy","17");
    }
    @Conditional(WinCondition.class)//Windows系统会注入该对象
    @Bean
    public Person lingling(){
        System.out.println("lingling加载到IOC容器里了");
        return new Person("lingling","22");
    }
    @Conditional(LinCondition.class)
    @Bean
    public Person daming(){
        System.out.println("daming加载到IOC容器里了");
        return new Person("daming","18");
    }
    @Bean
    public FactoryBeanDemo factoryBeanDemo(){
        System.out.println("FactoryBeanDemo加载到IOC容器里了");
        return new FactoryBeanDemo();
    }
}
