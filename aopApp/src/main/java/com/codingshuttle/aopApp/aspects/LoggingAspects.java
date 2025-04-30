package com.codingshuttle.aopApp.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
@Component
@Slf4j
public class LoggingAspects {

    //@Before("execution(* orderPackage(..))")
    //@Before("execution(* com.codingshuttle.aopApp.services.impl.ShipmentServiceImpl.orderPackage(..))")
    //@Before("execution(* com.codingshuttle.aopApp.services.impl.*.orderPackage(..))")
    @Before("execution(* com.codingshuttle.aopApp.services.impl.*.*(..))")
    public void beforeOrderPackage(JoinPoint joinPoint) {
        log.info("Before called from LoggingAspect kind, {}", joinPoint.getKind());
        log.info("Before called from LoggingAspect signature, {}", joinPoint.getSignature());
    }

    //@Before("within(com.codingshuttle.aopApp.services.impl.*)")
    //@Before("within(com.codingshuttle.aopApp..*)")
    @Before("MyLoggingAndAopMethodsPointcut()")
    public void beforeServiceImplCalls(JoinPoint joinPoint) {
        log.info("Service Impl calls");
    }

    //@Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    @Before("@annotation(com.codingshuttle.aopApp.aspects.MyLogging)")
    public void beforeTransactionalAnnotationCalls() {
       log.info("beforeTransactionalAnnotationCalls");
        log.info("Before My Logging Annotation Calls");
    }

    @Pointcut("@annotation(com.codingshuttle.aopApp.aspects.MyLogging) && within(com.codingshuttle.aopApp..*)")
    public void MyLoggingAndAopMethodsPointcut() {

    }

}
