# Spring知识学习
## Lesson 02
1. bean初始化、销毁实现
    - @Bean(initMethod="init",destroyMethod="destroy")
    - 实现InitialiazingBean、DisposableBean接口方法
    - 调用JSR250的注解，@PostConstruct、@PreDestroy