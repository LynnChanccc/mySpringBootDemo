package com.woniu.springboot.mapper;

import com.woniu.springboot.model.Task;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cl
 * @Date 2020/3/9 15:33
 */
@Repository
public interface TaskMapper {

    @Select("select * from task where taskname = #{taskName}")
    List<Task> select(String taskName);

    @Delete("delete from task where taskid = #{taskId}")
    void deleteTaskById(Integer taskId);
}
