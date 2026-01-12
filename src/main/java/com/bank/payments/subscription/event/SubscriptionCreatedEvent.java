package com.bank.payments.subscription.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class SubscriptionCreatedEvent {
    private Long subscriptionId;
    private String userId;
    private BigDecimal amount;
    private String frequency;
    private LocalDate nextRunDate;
}