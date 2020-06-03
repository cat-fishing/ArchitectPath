package com.scg.test.springtest.lesson_02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigLesson_02 {
    @Bean
    public Person xiaoming(){
        System.out.println("xiaoming同学加入IOC全家桶");
        return new Person("xiaoming","10");
    }
    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Person lingling(){
        System.out.println("lingling同学加入IOC全家桶");
        return new Person("lingling","14");
    }
    @Bean
    public Dog dog(){
        System.out.println("哈士奇即将加入拆家大队");
        return new Dog();
    }
    @Bean
    public Cat cat(){
        System.out.println("加菲即将进入战场》》》》》》");
        return new Cat();
    }
    @Bean
    public Plane plane(){
        System.out.println("歼20灰机，起飞……");
        return new Plane();
    }
    @Bean
    public Jeep jeep(){
        return new Jeep();
    }
}
