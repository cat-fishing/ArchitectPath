package com.scg.test.taskframe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 提交框架处理任务实体类
 */
public class JobInfo<R> {
    //任务名称
    private final String jobName;
    //任务数
    private final int jobLength;
    //具体任务
    private final ITaskProcesser<?,?> taskProcesser;
    //处理成功任务数
    private AtomicInteger successCount;
    //处理任务数
    private AtomicInteger taskProcesserCount;
    //任务处理结果,从头部拿、从尾部塞
    private LinkedBlockingDeque<TaskResult<R>> taskDetailQueue;
    //任务超时时间
    private final long expireTime;

    public JobInfo(String jobName, int jobLength, ITaskProcesser<?, ?> taskProcesser,long expireTime) {
        this.jobName = jobName;
        this.jobLength = jobLength;
        this.taskProcesser = taskProcesser;
        this.successCount = new AtomicInteger(0);
        this.taskProcesserCount = new AtomicInteger(0);
        this.taskDetailQueue = new LinkedBlockingDeque<>(jobLength);
        this.expireTime = expireTime;
    }

    public String getJobName() {
        return jobName;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public ITaskProcesser<?,?> getTaskProcesser(){
        return taskProcesser;
    }

    public int getSuccessCount() {
        return successCount.get();
    }

    public int getTaskProcesserCount() {
        return taskProcesserCount.get();
    }

    public int getFailCount(){
        return getTaskProcesserCount() - getSuccessCount();
    }

    public String getTotalProcess(){
        return "Success[" + getSuccessCount() + "]/Current[" + getTaskProcesserCount() + "]/Total["
                + jobLength + "]";
    }

    public List<TaskResult<R>> getTaskDetail(){
        List<TaskResult<R>> list = new ArrayList<>();
        TaskResult<R> task;
        while((task=taskDetailQueue.pollFirst())!=null){
            list.add(task);
        }
        return list;
    }

    public void addTaskResult(TaskResult<R> result){
        if(TaskResultType.Success.equals(result.getResultType())){
            successCount.incrementAndGet();
        }
        taskDetailQueue.addLast(result);
        taskProcesserCount.incrementAndGet();
    }
}
