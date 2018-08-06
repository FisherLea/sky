package com.sky.sample.cloud.task.job;

import com.sky.sample.cloud.common.task.core.SimpleTask;
import com.sky.sample.cloud.common.task.core.TaskContext;
import com.sky.sample.cloud.task.test.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * Created by viruser on 2018/8/6.
 */
@Component
@Configurable
@EnableScheduling
public class TestConnCloseTask implements SimpleTask {

    public static final Logger logger = LoggerFactory.getLogger(TestConnCloseTask.class);

    @Autowired
    private TestService testService;

    @Override
    public void execute(TaskContext context) {
        testService.orderList();
    }

    @Override
    public String getTaskName() {
        return "TestConnCloseTask";
    }
}
