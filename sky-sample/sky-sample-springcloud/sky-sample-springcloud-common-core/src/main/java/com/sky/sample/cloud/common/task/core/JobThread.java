package com.sky.sample.cloud.common.task.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by viruser on 2018/8/6.
 */
public class JobThread implements Runnable {

    public static final Logger logger = LoggerFactory.getLogger(JobThread.class);

    private SimpleTask simpleTask;

    public JobThread(SimpleTask simpleTask) {
        this.simpleTask = simpleTask;
    }

    @Override
    public void run() {
        logger.info("start task execute==============" + simpleTask.getTaskName());
        try{
            simpleTask.execute(new TaskContext());
        }catch (Exception e){
            logger.warn(e.getMessage(), e);
        }
    }
}
