package com.woniu.springboot.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author cl
 * @Date 2020/4/1 15:05
 * 拦截器配置
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    /**
     * 注册自定义拦截器
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 是配置需要拦截的方法，如果遇到拦截不同的Controller，
        // 我们可以使用不同的配置，比如：/user/* 在这里是可以配置多个拦截器的。
        registry.addInterceptor(new WebInterceptor()).addPathPatterns("/exception/*","/api/user/getUserList");
    }
}

