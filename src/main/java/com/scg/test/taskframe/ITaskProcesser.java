package com.scg.test.taskframe;

/**
 *要求框架使用者实现的方法
 */
public interface ITaskProcesser<T,R> {
    TaskResult<R> taskExecute(T data);
}
