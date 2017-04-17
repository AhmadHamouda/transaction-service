package com.n26.assignment.logging;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by Ahmad on 4/15/2017.
 */
@Aspect
@Component
public class LoggingInterceptor {

    private static final Logger LOG = Logger.getLogger(LoggingInterceptor.class);

    @Before("execution(* com.n26.assignment..*.*())")
    public void logBeforeMethod(JoinPoint joinPoint) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("UserDto :");
        logMessage.append("Anonymous user");
        logMessage.append(" ,Enter :");
        logMessage.append(joinPoint.getTarget().getClass().getName());
        logMessage.append(".");
        logMessage.append(joinPoint.getSignature().getName());

        LOG.info(logMessage.toString());

        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            StringBuilder logParammaters = new StringBuilder();
            logParammaters.append("Method paramaters ( ");
            for (Object arg : args) {
                logParammaters.append(arg == null ? "null" : arg).append(",");
            }
            if (args.length > 0) {
                logParammaters.deleteCharAt(logParammaters.length() - 1);
            }
            logParammaters.append(" ).");

            LOG.debug(logParammaters.toString());
        }

    }

    @AfterReturning("execution(* com.n26.assignment..*.*())")
    public void logAfterMethodSuccess(JoinPoint joinPoint) {

        StringBuilder logMessage = new StringBuilder();
        logMessage.append("Finish :");
        logMessage.append(joinPoint.getTarget().getClass().getName());
        logMessage.append(".");
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append("successfully.");

        LOG.info(logMessage.toString());

    }

    @AfterThrowing(pointcut = "execution(* com.n26.assignment..*.*())", throwing = "ex")
    public void logAfterMethodFaill(JoinPoint joinPoint, Throwable ex) {

        StringBuilder logMessage = new StringBuilder();
        logMessage.append("Finish :");
        logMessage.append(joinPoint.getTarget().getClass().getName());
        logMessage.append(".");
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append(", with error: ");
        logMessage.append(ex);

        LOG.error(logMessage.toString());
    }

}
