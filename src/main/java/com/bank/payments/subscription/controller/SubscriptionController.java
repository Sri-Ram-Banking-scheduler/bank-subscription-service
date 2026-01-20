package com.bank.payments.subscription.controller;

import com.bank.payments.subscription.dto.SubscriptionRequest;
import com.bank.payments.subscription.model.Subscription;
import com.bank.payments.subscription.service.AuditService;
import com.bank.payments.subscription.service.SubscriptionService;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class SubscriptionController {

    private final Tracer tracer;
    private final SubscriptionService subscriptionService;
    private final AuditService auditService;

    @PostMapping("/subscriptions")
    public ResponseEntity<?> create(@RequestBody SubscriptionRequest request) {
        Span span = tracer.nextSpan().name("create-subscription").start();
        try (Tracer.SpanInScope ws = tracer.withSpan(span)) {
            // your business logic
            Subscription subscription = subscriptionService.createSubscription(request);
            // Audit an event
            auditService.logAudit(request.getUserId(),"created Event", "registered for autopay");
            return ResponseEntity.ok(subscription);
        } finally {
            span.end();
        }
    }
}
