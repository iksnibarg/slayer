package pl.grabinski.slayer.util.performance;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnProperty(name="log.selected.method.execution.times", havingValue = "true", matchIfMissing = true)
public class PerformanceMonitor {

    private static final Logger log = LoggerFactory.getLogger(PerformanceMonitor.class);

    @Around("@annotation(LogExecutionTime)")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            if (log.isTraceEnabled()) {
                long duration = System.currentTimeMillis() - start;
                log.trace("Execution of {} took {} ms", joinPoint.getSignature().toShortString(), duration);
            }
        }
    }

}
