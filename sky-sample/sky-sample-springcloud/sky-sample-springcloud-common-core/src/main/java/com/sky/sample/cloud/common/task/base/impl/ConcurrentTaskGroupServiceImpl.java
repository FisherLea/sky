package com.sky.sample.cloud.common.task.base.impl;

import com.sky.sample.cloud.common.task.core.JobThread;
import com.sky.sample.cloud.common.task.core.SimpleTask;
import org.springframework.core.task.TaskExecutor;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * 并发执行的定时任务组实现
 * Created by viruser on 2018/8/6.
 */
public class ConcurrentTaskGroupServiceImpl extends AbstractTaskGroupServiceImpl {

    @Resource(name = "concurrentTaskExecutor")
    private TaskExecutor taskExecutor;

    @Override
    protected void doExecute() {
        List<SimpleTask> tasks = getTasks();
        Iterator<SimpleTask> iterator = tasks.iterator();
        while (iterator.hasNext()){
            SimpleTask task = iterator.next();
            taskExecutor.execute(new JobThread(task));
        }
    }
}
