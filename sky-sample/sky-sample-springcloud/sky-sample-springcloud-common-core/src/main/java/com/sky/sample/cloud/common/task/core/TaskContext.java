package com.sky.sample.cloud.common.task.core;

/**
 * Created by viruser on 2018/8/6.
 */
public class TaskContext {

    private String taskId;

    private String taskParameter;

    public TaskContext() {
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskParameter() {
        return taskParameter;
    }

    public void setTaskParameter(String taskParameter) {
        this.taskParameter = taskParameter;
    }
}
