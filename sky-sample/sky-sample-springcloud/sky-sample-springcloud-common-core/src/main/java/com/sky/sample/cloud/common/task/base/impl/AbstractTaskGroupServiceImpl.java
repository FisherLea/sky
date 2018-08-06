package com.sky.sample.cloud.common.task.base.impl;

import com.sky.sample.cloud.common.task.core.SimpleTask;

import java.util.List;

/**
 * Created by viruser on 2018/8/6.
 */
public abstract class AbstractTaskGroupServiceImpl extends AbstractQuartzTaskServiceImpl {

    private List<SimpleTask> tasks;

    public AbstractTaskGroupServiceImpl(){

    }

    public List<SimpleTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<SimpleTask> tasks) {
        this.tasks = tasks;
    }
}
