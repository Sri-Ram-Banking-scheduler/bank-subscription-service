package com.bank.payments.subscription.mapper;

import com.bank.payments.events.SubscriptionEvent;
import com.bank.payments.subscription.dto.SubscriptionRequest;
import com.bank.payments.subscription.dto.SubscriptionResponse;
import com.bank.payments.subscription.model.Subscription;
import io.micrometer.tracing.Tracer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class SubscriptionMapper {
    @Autowired
    protected Tracer tracer;

    // 1. Request -> Entity
    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "id", ignore = true)
    public abstract Subscription toEntity(SubscriptionRequest request);

    // 2. Entity -> Avro Event
    @Mapping(target = "subscriptionId", source = "id")
    @Mapping(target = "amount", source = "amount") // MapStruct converts BigDecimal to String/Double
    @Mapping(target = "type", constant = "CREATED")
    @Mapping(target = "traceId", expression = "java(getCurrentTraceId())")
    public abstract SubscriptionEvent toAvroEvent(Subscription entity);
    // 3. Entity -> Response DTO (The mapToResponse logic you had)
    public abstract SubscriptionResponse toResponse(Subscription entity);

    // Helper method to grab the traceId from the current thread context
    protected String getCurrentTraceId() {
        if (tracer != null && tracer.currentSpan() != null) {
            return tracer.currentSpan().context().traceId();
        }
        return "unknown";
    }
}