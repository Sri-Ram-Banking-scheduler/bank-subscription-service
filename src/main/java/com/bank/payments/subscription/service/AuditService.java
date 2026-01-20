package com.bank.payments.subscription.service;

import com.bank.payments.subscription.model.AuditLog;
import com.bank.payments.subscription.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditService {
    private final AuditRepository auditRepository; // SQL or NoSQL

    @Async("taskExecutor")
    public void logAudit(String userId, String action, String details) {
        // This log will automatically include the same Trace ID as the controller!
        log.info("Auditing action: {} for user: {}", action, userId);
        
        AuditLog audit = AuditLog.builder()
                .userId(userId)
                .action(action)
                .details(details)
                .timestamp(LocalDateTime.now())
                .build();
                
        auditRepository.save(audit);
    }
}