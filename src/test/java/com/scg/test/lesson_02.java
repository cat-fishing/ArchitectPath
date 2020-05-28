package com.scg.test;

import com.scg.test.springtest.lesson_02.ConfigLesson_02;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
public class lesson_02 {
    @Test
    public void demo(){
        AnnotationConfigApplicationContext app =
                    new AnnotationConfigApplicationContext(ConfigLesson_02.class);
        System.out.println("全家桶IOC容器初始化完毕");
        app.close();
    }
}
