package pl.grabinski.slayer.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceMonitor {

    private static final Logger log = LoggerFactory.getLogger(PerformanceMonitor.class);

    @Around("execution(* pl.grabinski.slayer.InstanceRetriever.*(..)) || " +
            "execution(* pl.grabinski.slayer.openstack.*.*(..))")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        }
        finally {
            long duration = System.currentTimeMillis() - start;
            log.trace("Execution of {} took {} ms", joinPoint.getSignature().toShortString(), duration);
        }
    }

}
