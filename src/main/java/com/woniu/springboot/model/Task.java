package com.woniu.springboot.model;

import lombok.Data;

import java.util.Date;

/**
 * @author cl
 * @Date 2020/3/9 15:28
 * 定时任务 实体类
 */
@Data
public class Task {
    private Integer taskId;
    private String taskName;
    private String taskDesc;
    private Integer taskDataId;
    private Date taskTime;
}
