package com.gemapi.aspect;

import com.gemapi.annotation.Auditable;
import com.gemapi.dto.UserDTO;
import com.gemapi.entity.AuditLog;
import com.gemapi.repository.AuditLogRepository;
import com.gemapi.service.UserContextService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AuditLogAspect {

    private final AuditLogRepository auditLogRepository;
    private final UserContextService userContextService;
    private final ObjectMapper objectMapper;

    public AuditLogAspect(AuditLogRepository auditLogRepository,
                         UserContextService userContextService,
                         ObjectMapper objectMapper) {
        this.auditLogRepository = auditLogRepository;
        this.userContextService = userContextService;
        this.objectMapper = objectMapper;
    }

    @Around("@annotation(auditable)")
    public Object auditMethod(ProceedingJoinPoint joinPoint, Auditable auditable) throws Throwable {
        LocalDateTime timestamp = LocalDateTime.now();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        UserDTO currentUser = userContextService.getCurrentUser();

        // Build initial audit log
        AuditLog.AuditLogBuilder logBuilder = AuditLog.builder()
                .action(auditable.action())
                .entityName(className)
                .timestamp(timestamp);

        if (currentUser != null) {
            logBuilder.userId(currentUser.getId())
                     .userEmail(currentUser.getEmail());
        }

        try {
            // Get method arguments
            String args = Arrays.toString(joinPoint.getArgs());
            logBuilder.details("Method: " + methodName + ", Arguments: " + args);

            // Execute the method
            Object result = joinPoint.proceed();

            // If the result has an ID field, try to extract it
            if (result != null) {
                try {
                    String resultJson = objectMapper.writeValueAsString(result);
                    logBuilder.entityId(extractId(result));
                    logBuilder.details(logBuilder.build().getDetails() + ", Result: " + resultJson);
                } catch (Exception e) {
                    log.warn("Could not serialize result", e);
                }
            }

            // Set success status
            logBuilder.status("SUCCESS");
            
            // Save audit log
            auditLogRepository.save(logBuilder.build());
            
            return result;
        } catch (Exception e) {
            // Log the error
            log.error("Error in {}.{}: {}", className, methodName, e.getMessage(), e);
            
            // Set error information in audit log
            logBuilder.status("ERROR")
                     .errorMessage(e.getMessage());
            
            // Save audit log
            auditLogRepository.save(logBuilder.build());
            
            throw e;
        }
    }

    private String extractId(Object obj) {
        try {
            // Try to get id using reflection
            return obj.getClass().getMethod("getId").invoke(obj).toString();
        } catch (Exception e) {
            return "N/A";
        }
    }
} 