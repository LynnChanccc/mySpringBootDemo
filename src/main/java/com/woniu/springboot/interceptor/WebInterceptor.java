package com.woniu.springboot.interceptor;


import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cl
 * @Date 2020/4/1 14:53
 * 拦截器
 */
public class WebInterceptor implements HandlerInterceptor {
    /**
     * 请求处理之前调用的方法 该方法将在Controller处理之前进行调用
     * 可以用于登录验证、权限验证等等
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod  = (HandlerMethod) handler;
        System.out.println("拦截的Controller:"+handlerMethod.getBeanType().getName());
        System.out.println("拦截的请求:"+handlerMethod.getMethod().getName());
        System.out.println("请求处理之前");
        //返回true方形，返回false不在向下执行
        return true;
    }

    /**
     * 请求处理之后
     * 方法在出行完处理器之后，进入视图解析器之前执行，可以对视图进行处理
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("请求处理之后");
    }

    /**
     * 请求处理之后，提交到界面之前
     * 主要用于关闭资源
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("请求处理之后，提交到界面之前");
    }
}
