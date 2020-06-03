# Spring知识学习
## Lesson 02
1. Bean初始化、销毁实现
    - @Bean(initMethod="init",destroyMethod="destroy")
    - 实现InitialiazingBean、DisposableBean接口方法
    - 调用JSR250的注解，@PostConstruct、@PreDestroy
2. BeanPostProcessor
    前置处理器、后置处理器
3. ApplicationContextAware
    服务启动将IOC容器注入，以便业务调用
    