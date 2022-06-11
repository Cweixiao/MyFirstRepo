package com.example.demo.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 *
 * Prints request and response information
 */
@Aspect
@Component
public class WebLogAspect {

    private final Logger log = LoggerFactory.getLogger(WebLogAspect.class);


    //Package to set breakpoints
    @Pointcut("execution(public * com.example.demo.file.Image2Identification.controller.*.*(..))")
    public void WebLog(){

    }


    /**
     * Execute before WebLog()
     * When receiving a request, record the request content
     * @param joinPoint
     */
    @Before("WebLog()")
    public void doBefore(JoinPoint joinPoint){

        //Get the request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //Get information from the request
        log.info("URL : "+request.getRequestURI().toString());
        log.info("HTTP_METHOD : "+request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : "+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        log.info("ARGS : "+ Arrays.toString(joinPoint.getArgs()));

    }



    /**
     * The request is processed and the content is returned
     * @param res
     */
    @AfterReturning(returning = "res",pointcut = "WebLog()")
    public void doAfterReturning(Object res) throws JsonProcessingException {

        //Returns text in JSON format
        log.info("RESPONSE : "+new ObjectMapper().writeValueAsString(res));
    }

}
