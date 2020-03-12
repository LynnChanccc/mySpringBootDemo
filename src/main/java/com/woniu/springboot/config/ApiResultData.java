package com.woniu.springboot.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cl
 * @Date 2020/3/6 13:56
 * 统一返回数据
 */
@Data
public class ApiResultData {
    private Integer code;
    private String desc;
    private Object data;
    private boolean success;

    //默认成功构造
    public ApiResultData() {
        code = 1;
        success = true;
    }
}
