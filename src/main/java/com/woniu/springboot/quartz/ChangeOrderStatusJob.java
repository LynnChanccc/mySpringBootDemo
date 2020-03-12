package com.woniu.springboot.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author cl
 * @Date 2020/3/9 15:41
 * 修改订单状态任务（具体的业务逻辑）
 */
@Component
@Scope("prototype")
public class ChangeOrderStatusJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //接受创建定时任务时传入的参数
        Integer orderId = (Integer) jobExecutionContext.getMergedJobDataMap().get("orderId");
        System.out.println("修改订单状态的任务开始执行，订单id:"+orderId);
        try {
            //任务执行完毕，删除该任务
            jobExecutionContext.getScheduler().deleteJob(JobKey.jobKey("changeOrderStatus"+orderId));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
