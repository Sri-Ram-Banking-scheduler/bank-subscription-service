package com.bank.payments.subscription.controller;

import com.bank.payments.subscription.dto.SubscriptionRequest;
import com.bank.payments.subscription.model.Subscription;
import com.bank.payments.subscription.service.SubscriptionService;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class SubscriptionController {

    private final Tracer tracer;
    private final SubscriptionService subscriptionService;

    public SubscriptionController(Tracer tracer,  SubscriptionService subscriptionService) {
        this.tracer = tracer;
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/subscriptions")
    public ResponseEntity<?> create(@RequestBody SubscriptionRequest request) {
        Span span = tracer.nextSpan().name("create-subscription").start();
        try (Tracer.SpanInScope ws = tracer.withSpan(span)) {
            // your business logic
            Subscription subscription = subscriptionService.createSubscription(request);
            return ResponseEntity.ok(subscription);
        } finally {
            span.end();
        }
    }
}
