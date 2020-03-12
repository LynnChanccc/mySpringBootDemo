package com.woniu.springboot.quartz;

import com.woniu.springboot.mapper.TaskMapper;
import com.woniu.springboot.model.Task;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author cl
 * @Date 2020/3/9 15:32
 * 动态任务，每隔一段时间扫描任务表查询是否有新任务
 */
@Component
public class DynamicTaskJob implements Job {

    @Autowired
    private TaskMapper taskMapper;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("扫描任务表");
        List<Task> list = taskMapper.select("订单超时");
        //每一批超时的订单定义一个定时任务来处理
        Scheduler scheduler = jobExecutionContext.getScheduler();
        //传参
        for (Task task : list){
            System.out.println("有新的任务");
            //获取任务执行时间
            Date taskTime = task.getTaskTime();
            //定义修改订单状态的任务
            JobDetail jobDetail = JobBuilder.newJob(ChangeOrderStatusJob.class)
                    //任务识别，用来唯一标识该任务
                    .withIdentity("changeOrderStatus" + task.getTaskDataId())
                    .build();
            //将数据传入到目标任务中
            jobDetail.getJobDataMap().put("orderId",task.getTaskDataId());
            //创建简单任务触发器
            SimpleScheduleBuilder ssb = SimpleScheduleBuilder.simpleSchedule();
            SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                    //任务开始时间
                    .startAt(taskTime)
                    //触发器唯一标识
                    .withIdentity("changeOrderTrigger", "orderTrigger")
                    .withSchedule(ssb)
                    .build();

            try {
                //使用调度器管理该任务
                scheduler.scheduleJob(jobDetail,simpleTrigger);
                //删除task表中的该任务数据
                taskMapper.deleteTaskById(task.getTaskId());
            }catch (SchedulerException e){
                e.printStackTrace();
            }
        }

    }
}
