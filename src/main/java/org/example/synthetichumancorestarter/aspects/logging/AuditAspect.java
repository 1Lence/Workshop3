package org.example.synthetichumancorestarter.aspects.logging;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.synthetichumancorestarter.annotations.WeylandWatchingYou;
import org.example.synthetichumancorestarter.aspects.dto.AuditDto;
import org.example.synthetichumancorestarter.aspects.dto.LogData;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {
    private final ConsoleLoggingAudit consoleLoggingAudit;

    @Pointcut("@annotation(weyland)")
    public void isMethodHasAnnotationWeylandWatchingYou(WeylandWatchingYou weyland) {
    }

    @Around("isMethodHasAnnotationWeylandWatchingYou(weyland)")
    public Object consoleLog(ProceedingJoinPoint proceedingJoinPoint, WeylandWatchingYou weyland) throws Throwable {
        Object result = proceedingJoinPoint.proceed();

        AuditDto auditDto = AuditDto.builder()
                .loggingType(weyland.type())
                .topic(weyland.topic())
                .build();

        LogData logData = LogData.builder()
                .methodName(proceedingJoinPoint.getSignature().getName())
                .args(proceedingJoinPoint.getArgs())
                .result(result)
                .build();

        consoleLoggingAudit.sendLogs(auditDto, logData);

        return result;
    }
}