package com.scg.test.springtest.lesson_02;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Cat {

    @PostConstruct
    public void init(){
        System.out.println("加菲进食啦-----@PostConstruct----");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("加菲要睡觉觉啦--------@PreDestroy-----");
    }
}
