package com.woniu.springboot.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cl
 * @Date 2020/3/9 14:38
 * 定时任务配置类
 */
@Configuration
public class QuartzConfig {

    /*//测试定时任务1的任务描述
    @Bean
    public JobDetail testQuartz01() {
        return JobBuilder.newJob(TestTask1.class).withIdentity("testTask1").storeDurably().build();
    }

    //测试定时任务1的触发器
    @Bean
    public Trigger testQuartz01Trigger() {
        //5秒执行一次
        SimpleScheduleBuilder ssb = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(testQuartz01())
                .withIdentity("testTask1")
                .withSchedule(ssb)
                .build();

    }*/

//    //任务2的描述
//    @Bean
//    public JobDetail testQuartz02() {
//        return JobBuilder.newJob(TestTask2.class).withIdentity("testTask2").storeDurably().build();
//    }
//
//    //任务2的触发器
//    @Bean
//    public Trigger testQuartz02Trigger() {
//        //corn的方式，每5秒执行一次
//        return TriggerBuilder.newTrigger().forJob(testQuartz02())
//                .withIdentity("testTask2")
//                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
//                .build();
//    }

    //轮循动态任务的描述
    @Bean
    public JobDetail dynamicTaskQuartz() {
        return JobBuilder.newJob(DynamicTaskJob.class).withIdentity("dynamicTask").storeDurably().build();
    }

    //轮循动态任务触发器
    @Bean
    public Trigger dynamicTaskTrigger() {
        //每5秒执行一次动态任务
        return TriggerBuilder.newTrigger().forJob(dynamicTaskQuartz())
                .withIdentity("dynamicTask")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
                .build();
    }
}
