package com.woniu.springboot.api;

import com.alibaba.fastjson.JSONObject;
import com.woniu.springboot.config.ApiResultData;
import com.woniu.springboot.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @author Lynn
 * 2019-09-26
 */
@RestController
@RequestMapping("/exception")
@Api(tags = "异常测试接口")
public class TestController {
    @GetMapping("testException")
    @ApiOperation("异常测试")
    public String testException() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前请求的request对象
        Enumeration<String> headerNames = request.getHeaderNames();
        HashMap hashMap = new HashMap();
        //遍历请求头
        while (headerNames.hasMoreElements()){
            String key = headerNames.nextElement();
            System.out.println(key);//请求头
            System.out.println(request.getHeader(key));//对应的值
            hashMap.put(key,request.getHeader(key));
        }
        System.out.println(hashMap);
        throw new Exception("异常测试");
    }
}
