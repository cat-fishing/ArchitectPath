package com.scg.test;

import com.scg.test.springtest.lesson_01.ConfigDemo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class lesson_01 {

	@Test
	public void contextLoads() {
		long c = System.nanoTime();
		long d = TimeUnit.NANOSECONDS.convert(60,TimeUnit.MILLISECONDS)
				+ c;
		System.out.println("当前时间："+c);
		System.out.println("过期时间："+d);
		System.out.println("超时时间："+TimeUnit.NANOSECONDS.toMillis(d-c));
	}
	@Test
	public void scopeTest(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(ConfigDemo.class);
		System.out.println("IOC容器创建完毕----------------");
		String[] name = app.getBeanDefinitionNames();
		Arrays.stream(name).forEach(System.out::println);
		System.out.println("打印对象id--------------------");
		System.out.println(app.getBean("tom"));
		System.out.println(app.getBean("jerry"));
		System.out.println(app.getBean("jerry"));
		System.out.println(app.getBean("jessy"));
	}
}
