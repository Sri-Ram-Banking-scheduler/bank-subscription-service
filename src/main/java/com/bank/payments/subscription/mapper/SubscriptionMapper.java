package com.bank.payments.subscription.mapper;

import com.bank.payments.events.SubscriptionEvent;
import com.bank.payments.subscription.dto.SubscriptionRequest;
import com.bank.payments.subscription.dto.SubscriptionResponse;
import com.bank.payments.subscription.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    // 1. Request -> Entity
    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "id", ignore = true)
    Subscription toEntity(SubscriptionRequest request);

    // 2. Entity -> Avro Event
    @Mapping(target = "subscriptionId", source = "id")
    @Mapping(target = "amount", source = "amount") // MapStruct converts BigDecimal to String/Double
    @Mapping(target = "type", constant = "CREATED")    SubscriptionEvent toAvroEvent(Subscription entity);
    // 3. Entity -> Response DTO (The mapToResponse logic you had)
    SubscriptionResponse toResponse(Subscription entity);
}