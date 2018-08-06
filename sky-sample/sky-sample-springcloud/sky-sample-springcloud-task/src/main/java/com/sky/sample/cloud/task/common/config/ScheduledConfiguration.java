package com.sky.sample.cloud.task.common.config;

import com.sky.sample.cloud.common.constants.JobConstant;
import com.sky.sample.cloud.common.task.base.QuartzTaskService;
import com.sky.sample.cloud.common.task.base.impl.ConcurrentTaskGroupServiceImpl;
import com.sky.sample.cloud.common.task.core.SimpleTask;
import com.sky.sample.cloud.task.job.TestConnCloseTask;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by viruser on 2018/8/6.
 */
@Configuration
public class ScheduledConfiguration {

    public static final int CORE_POOL_SIZE = 10;
    public static final int MAX_POOL_SIZE = 100;
    public static final int QUEUE_CAPACITY = 10;
    public static final int KEEP_ALIVE_SECONDS = 300;

    @Autowired
    private TestConnCloseTask testConnCloseTask;

    @Bean(name = "concurrentTaskExecutor")
    @Qualifier("concurrentTaskExecutor")
    public Executor concurrentTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        return executor;
    }

    //每分钟执行开始--begin
    @Bean(name = "everyMinuteConcurrentTaskGroupService")
    @Qualifier("everyMinuteConcurrentTaskGroupService")
    public QuartzTaskService everyMinuteConcurrentTaskGroupService(){
        ConcurrentTaskGroupServiceImpl groupService = new ConcurrentTaskGroupServiceImpl();
        List<SimpleTask> tasks = new ArrayList<>();
        tasks.add(testConnCloseTask);
        groupService.setTasks(tasks);

        return groupService;
    }

    @Bean(name = "everyMinuteJobDetailFactoryBean")
    @Qualifier("everyMinuteJobDetailFactoryBean")
    public MethodInvokingJobDetailFactoryBean everyMinuteJobDetailFactoryBean(){
        MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
        jobDetailFactoryBean.setTargetObject(everyMinuteConcurrentTaskGroupService());
        jobDetailFactoryBean.setTargetMethod("execute");
        jobDetailFactoryBean.setConcurrent(false);
        return jobDetailFactoryBean;
    }

    @Bean(name = "everyMinuteTriggerFactoryBean")
    @Qualifier("everyMinuteTriggerFactoryBean")
    public CronTriggerFactoryBean everyMinuteTriggerFactoryBean(){
        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
        triggerFactoryBean.setJobDetail(everyMinuteJobDetailFactoryBean().getObject());
        triggerFactoryBean.setCronExpression(JobConstant.JOB_EVERY_MINUTE_1);
        return triggerFactoryBean;
    }
    //每分钟执行结束--end

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        CronTrigger[] triggers = new CronTrigger[]{
                everyMinuteTriggerFactoryBean().getObject()
        };
        bean.setTriggers(triggers);
        return bean;
    }
}
