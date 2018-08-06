package com.sky.sample.cloud.common.task.core;

/**
 * Created by viruser on 2018/8/6.
 */
public interface SimpleTask {

    void execute(TaskContext context);

    String getTaskName();
}
