package com.scg.test.taskframe;

import java.util.concurrent.DelayQueue;

/**
 * 任务完成后，一定时间内提供查询；过期之后释放内存资源，定期处理过期任务
 */
public class CheckJobProcessor {
    //存放已完成任务等待过期队列
    private static DelayQueue<ItemVO<String>> queue = new DelayQueue<>();

    private CheckJobProcessor(){}

    private static class CheckJobProcessorHandler{
        public static CheckJobProcessor checkJobProcessor = new CheckJobProcessor();
    }

    public static CheckJobProcessor getInstance(){
        return CheckJobProcessorHandler.checkJobProcessor;
    }
    
    private static class FetchJob implements Runnable{
        @Override
        public void run() {
            while (true){
                try{
                    ItemVO<String> item = queue.take();
                    String jobName = item.getData();
                    PendingJobPool.getMap().remove(jobName);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    //任务完成后放入等候删除队列
    public void putJob(String jobName,long expireTime){
        queue.offer(new ItemVO<String>(expireTime,jobName));
    }
    static{
        Thread t = new Thread(new FetchJob());
        t.setDaemon(true);
        t.start();
    }
}
