package com.woniu.springboot.model;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author cl
 * @Date 2020/3/6 12:43
 */
@Data
@ApiModel
public class User{

    /**
     *---@ExcelProperty(index = 2,value = "用户年龄")
     * 表头标题，index支持自定义排序，默认是按照属性顺序排
     */
    @ApiModelProperty(value = "年龄")
//    @ExcelProperty(index = 2,value = "用户年龄")
    private Integer age;

//    @ExcelProperty(index = 0,value = "用户编号")
    @ApiModelProperty(value = "编号")
    //默认所有字段都会写入excel，这个注解会忽略这个字段
    @ExcelIgnore
    private Long id;

    @ApiModelProperty(value = "姓名")
//    @ExcelProperty(index = 1,value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "性别")
//    @ExcelProperty(index = 3,value = "用户性别")
    private String sex;

    public User(Integer age, Long id, String name, String sex) {
        this.age = age;
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    public User() {
    }
}
