package com.scg.test.taskframe;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 框架主体类，即调用者主要使用的类
 */
public class PendingJobPool {
    //获取当前CPU核心数
    private static final int THREAD_COUNTS = Runtime.getRuntime().availableProcessors();
    //任务队列
    private static BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(5000);
    //自定义固定大小，有界队列
    private static ExecutorService taskExecutor =
            new ThreadPoolExecutor(THREAD_COUNTS,THREAD_COUNTS,60, TimeUnit.SECONDS,taskQueue);
    //任务存储容器
    private static ConcurrentHashMap<String,JobInfo<?>> jobInfoMap = new ConcurrentHashMap<>();
    //销毁队列
    private static CheckJobProcessor checkJobProcessor = CheckJobProcessor.getInstance();
    //单例模式
    private PendingJobPool(){}
    //内部类实现线程安全单例
    private static class JobPoolHolder{
        public static PendingJobPool pool = new PendingJobPool();
    }

    public static PendingJobPool getInstance(){
        return JobPoolHolder.pool;
    }
    //调用者注册任务
    public <R> void registerJob(String jobName,int jobLength,
                            ITaskProcesser<?,?> taskProcesser, long expireTime){
        if(jobInfoMap.putIfAbsent(jobName,new JobInfo<R>(jobName,jobLength,taskProcesser,expireTime))!=null)
            throw new RuntimeException(jobName+"已经注册");
    }
    //暴漏容器对象
    public static Map<String,JobInfo<?>> getMap(){return jobInfoMap;}
    //对任务进行包装再交给线程池处理
    private static class PendingTask<T,R> implements Runnable{
        private JobInfo<R> jobInfo;
        private T processData;
        public PendingTask(JobInfo<R> jobInfo,T processData){
            this.jobInfo = jobInfo;
            this.processData = processData;
        }
        @Override
        public void run() {
            R r = null;
            ITaskProcesser<T,R> taskProcesser = (ITaskProcesser<T, R>) jobInfo.getTaskProcesser();
            TaskResult<R> result = null;
            try{
                //调用人实现方法
                result = taskProcesser.taskExecute(processData);
                if(result==null)
                    result = new TaskResult<>(TaskResultType.Exception,r,"result is null");
                if(result.getResultType()==null){
                    if(result.getReason()==null){
                        result = new TaskResult<>(TaskResultType.Exception,r,"reason is null");
                    }else{
                        result = new TaskResult<>(TaskResultType.Exception,r,
                                "resultType is null,but reason is"+result.getReason());
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                result = new TaskResult<R>(TaskResultType.Exception,r,
                        e.getMessage());
            }finally {
                jobInfo.addTaskResult(result);
                checkJobProcessor.putJob(jobInfo.getJobName(),jobInfo.getExpireTime());
            }
        }
    }
    //调用者提交任务
    public <T,R> void putTask(String jobName,T t){
        JobInfo<R> jobInfo = getJob(jobName);
        PendingTask<T,R> task = new PendingTask<>(jobInfo,t);
        taskExecutor.execute(task);
    }
    //获取任务
    private <R> JobInfo<R> getJob(String jobName){
        JobInfo<R> jobInfo = (JobInfo<R>) jobInfoMap.get(jobName);
        if(jobInfo==null)
            throw new RuntimeException(jobName+"方法未注册");
        return jobInfo;
    }
    //获取任务处理详情
    public <R> List<TaskResult<R>> getTaskDetail(String jobName){
        JobInfo<R> jobInfo = getJob(jobName);
        return jobInfo.getTaskDetail();
    }
    //获取工作整体进度
    public <R> String getTaskProgress(String jobName){
        JobInfo<R> jobInfo = getJob(jobName);
        return jobInfo.getTotalProcess();
    }

}
