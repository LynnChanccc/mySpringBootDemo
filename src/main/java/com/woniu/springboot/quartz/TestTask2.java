package com.woniu.springboot.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author cl
 * @Date 2020/3/9 14:35
 * 测试定时任务2
 */
public class TestTask2 extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        System.out.println("TestQuartz02-----:" + sdf.format(new Date()));
    }
}
