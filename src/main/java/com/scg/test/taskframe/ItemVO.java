package com.scg.test.taskframe;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 过期实体
 * @param <T>
 */
public class ItemVO<T> implements Delayed{
    //过期时间
    private long activeTime;
    //业务数据
    private T data;

    public ItemVO(long activeTime,T data){
        this.activeTime = TimeUnit.NANOSECONDS.convert(activeTime,TimeUnit.MILLISECONDS)
                 + System.nanoTime();
        this.data = data;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public T getData() {
        return data;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.activeTime-System.nanoTime(),TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long d = getDelay(TimeUnit.NANOSECONDS)-o.getDelay(TimeUnit.NANOSECONDS);
        return (d==0)?0:(d>0?1:-1);
    }
}
