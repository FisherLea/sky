package com.sky.sample.cloud.common.task.base.impl;

import com.sky.sample.cloud.common.task.base.QuartzTaskService;

/**
 * Created by viruser on 2018/8/6.
 */
public abstract class AbstractQuartzTaskServiceImpl implements QuartzTaskService {

    public AbstractQuartzTaskServiceImpl(){

    }

    @Override
    public void execute() {
        //前面增加定时任务过滤器
        doExecute();
    }

    protected abstract void  doExecute();
}
