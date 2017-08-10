package com.lsm.boot.shiro.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class WebLogAspect {
    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.lsm.boot.shiro.controller.*.*(..))")
    public void webLog(){
        //nothing to do
    }

    @Around("webLog()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("URL:{}, IP:{}, CLASS_METHOD:{}, ARGS:{}"
                , request.getRequestURL().toString()
                , request.getRemoteAddr()
                , joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()
                , Arrays.toString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        // 处理完请求，返回内容
        log.info("RESPONSE:{}, SPEND TIME:{}ms", result, System.currentTimeMillis() - startTime.get());
        return result;
    }
}
