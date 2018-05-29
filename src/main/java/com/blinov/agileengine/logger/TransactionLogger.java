package com.blinov.agileengine.logger;

import com.blinov.agileengine.response.BaseResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionLogger {

    private static final Logger LOGGER = LogManager.getLogger(TransactionLogger.class.getName());

    @Before("execution(* com.blinov.agileengine.controller.TransactionController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        LOGGER.info(joinPoint.getSignature().getName() + " execution");
    }

    @AfterReturning(pointcut = "execution(* com.blinov.agileengine.controller.TransactionController.*(..))", returning = "response")
    public void logAfter(BaseResponse response) {
        if (response.getStatusCode() == 200) {
            LOGGER.info(response.toString());
        } else {
            LOGGER.warn(response.toString());
        }
    }
}
