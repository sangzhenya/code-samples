package org.xinyue.xsecurity.web.aspect;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private Log log = LogFactory.getLog(LogAspect.class);

    @Around("execution(* org.xinyue.xsecurity.web.controller.IndexController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        log.info("Log aspect start");
        long start = System.currentTimeMillis();
        Object object = pjp.proceed();
        log.info("Log aspect spend:"+ (System.currentTimeMillis() - start));
        log.info("Log aspect end");
        return object;
    }
}
