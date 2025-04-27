package com.codingshuttle.aopApp.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspectV2  {

    @Before("allServiceMethodsPointCut()")
    public void beforeServiceMethodCalls(JoinPoint joinPoint) {
        log.info("Before advice method call, {}", joinPoint.getSignature());
    }

    @After("allServiceMethodsPointCut()")
    public void afterServiceMethodCalls(JoinPoint joinPoint) {
        log.info("After advice method call, {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "allServiceMethodsPointCut()", returning = "returnedObj")
    public void afterReturningServiceMethodCalls(JoinPoint joinPoint, Object returnedObj) {
        log.info("After returning advice method call, {}", joinPoint.getSignature());
        log.info("After returning returned value, {}", returnedObj);
    }

    @AfterThrowing(value = "allServiceMethodsPointCut()")
    public void afterThrowingServiceMethodCalls(JoinPoint joinPoint) {
        log.info("After Throwing advice method call, {}", joinPoint.getSignature());
    }

    @Pointcut("execution(* com.codingshuttle.aopApp.services.impl.*.*(..))")
    public void allServiceMethodsPointCut() {
    }
}
