package com.example.reservation.aspect;

import com.example.reservation.entity.AuditLog;
import com.example.reservation.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditLogRepository repository;

    @AfterReturning("@annotation(com.example.reservation.aspect.Auditable)")
    public void audit(JoinPoint joinPoint) {

        AuditLog log = new AuditLog();

        MethodSignature signature =
                (MethodSignature) joinPoint.getSignature();

        Auditable annotation =
                signature.getMethod()
                        .getAnnotation(Auditable.class);

        log.setAction(annotation.value());

        log.setCreatedAt(
                LocalDateTime.now()
        );

        repository.save(log);
    }
}
