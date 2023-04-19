package com.example.demo.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class TimedAPICallAspect {

    @Around("@annotation(com.example.demo.utils.TimedAPICall)")
    public Object timeAPICall(ProceedingJoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("API Call: {} {}", request.getMethod(), request.getRequestURI());

        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - startTime;
            log.info("Execution time: {} ms", executionTime);
            return result;
        } catch (Throwable throwable) {
            long executionTime = System.currentTimeMillis() - startTime;
            log.info("Execution time: {} ms", executionTime);
            throw throwable;
        }
    }
}
