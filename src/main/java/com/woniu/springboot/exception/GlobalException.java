package com.woniu.springboot.exception;

import com.alibaba.fastjson.JSONObject;
import com.woniu.springboot.config.ApiResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cl
 * @Date 2020/3/4 13:49
 * 运行时全局统一异常捕获处理器
 */
@ControllerAdvice
//如果不想每次都写private static final Logger logger = LoggerFactory.getLogger(当前类名.class);
// 可以用注解@Slf4j;
@Slf4j
public class GlobalException {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e, HttpServletRequest request) {
        //开发调试的时候可以在控制台打印堆栈异常信息
        e.printStackTrace();
        String s = JSONObject.toJSONString(e);
        //详细异常错误信息，定位到某一行,severe级别最高，严重错误
        log.error("=====具体异常信息日志:[{}]", s);
        //只打印异常信息，不定位到具体某一行
        log.error("=====简单异常信息日志:[{}]", e.getMessage());
        ApiResultData apiResultData = new ApiResultData(0, "程序异常");
        String string = JSONObject.toJSONString(apiResultData);
        return string;
    }

}
