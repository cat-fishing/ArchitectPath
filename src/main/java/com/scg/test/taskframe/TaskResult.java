package com.scg.test.taskframe;

/**
 * 任务处理返回结果
 */
public class TaskResult<R> {
    private final TaskResultType resultType;
    //任务处理结果
    private final R returnValue;
    //任务处理失败原因
    private final String reason;

    public TaskResult(TaskResultType resultType, R returnValue, String reason) {
        this.resultType = resultType;
        this.returnValue = returnValue;
        this.reason = reason;
    }

    public TaskResult(TaskResultType resultType, R returnValue) {
        this.resultType = resultType;
        this.returnValue = returnValue;
        this.reason = "Success";
    }

    public TaskResultType getResultType() {
        return resultType;
    }

    public R getReturnValue() {
        return returnValue;
    }

    public String getReason() {
        return reason;
    }
}
