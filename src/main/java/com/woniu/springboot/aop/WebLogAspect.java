package com.woniu.springboot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author cl
 * @Date 2020/3/21 13:52
 * aop 切面类 实现web层的日志切面
 * 要想把一个类变成切面类，需要两步，
 * ① 在类上使用 @Component 注解 把切面类加入到IOC容器中
 * ② 在类上使用 @Aspect 注解 使之成为切面类
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    /**
     * 定义切入点，切入点是com.woniu.springboot.api包下所有类的所有方法
     */
    @Pointcut("execution(public * com.woniu.springboot.api..*.*(..))")
    public void webLog() {
    }

    /**
     * 用于匹配连接点所在的Java类或者包。
     */
    public static final String POINT_CUT = "within(com.woniu.springboot.api.TestController)";

    /**
     * 前置通知：在连接点之前执行的通知,方法调用前被调用
     * 在某连接点之前执行的通知，除非抛出一个异常，否则这个通知不能阻止连接点之前的执行流程。
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录下请求内容
        log.info("请求URI:" + request.getRequestURI().toString());
        log.info("请求方式:" + request.getMethod());
        log.info("IP:" + request.getRemoteAddr());
        log.info("方法:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("参数:" + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 后置通知，处理完业务逻辑之后的通知
     *
     * @param result
     * @throws Throwable
     */
    @AfterReturning(returning = "result", pointcut = "webLog()")
    public void doAfterReturning(Object result) throws Throwable {
        //处理完请求返回内容
        log.info("请求返回数据:" + result);
    }

    /**
     * 环绕通知：环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，、
     * 执行完毕是否需要替换返回值。环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around(value = POINT_CUT)
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("环绕通知的目标方法名:" + proceedingJoinPoint.getSignature().getName());
        try {
            //这里可以做一些额外操作
            System.out.println("前置增强");
            Object obj = proceedingJoinPoint.proceed();//方法执行
            System.out.println("后置增强");
            return obj;
        } catch (Throwable throwable) {
            System.out.println("异常增强");
            throwable.printStackTrace();
        }
        return null;
    }

}
