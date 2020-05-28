package com.scg.test.springtest.lesson_02;

public class Person {
    private String name;
    private String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public void init(){
        System.out.println("调用初始化init方法");
    }
    public void destroy(){
        System.out.println("调用销毁destory方法");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
