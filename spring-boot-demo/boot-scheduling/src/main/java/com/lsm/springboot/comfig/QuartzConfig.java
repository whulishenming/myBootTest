package com.lsm.springboot.comfig;

import com.lsm.springboot.task.ScheduledTest2;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduledTest2 task){
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        //是否并发执行
        jobDetail.setConcurrent(false);
        jobDetail.setTargetObject(task);
        jobDetail.setTargetMethod("executeFileDownLoadTask");
        return jobDetail;
    }

    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(@Qualifier("jobDetail") MethodInvokingJobDetailFactoryBean jobDetail) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(jobDetail.getObject());
        tigger.setCronExpression("0/5 * * * * ? ");// 每5秒执行一次
        return tigger;

    }

    /**
     * quartz调度工厂
     */
    @Bean
    public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        //用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        bean.setOverwriteExistingJobs(true);
        //用户Quartz集群
//        bean.setDataSource(dataSource);
        //延时启动，应用启动20秒后
        bean.setStartupDelay(20);
        //注册触发器
        bean.setTriggers(cronJobTrigger);
        return bean;
    }

}
