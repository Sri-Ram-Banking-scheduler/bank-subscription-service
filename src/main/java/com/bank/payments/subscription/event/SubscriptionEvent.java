package com.bank.payments.subscription.event;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class SubscriptionEvent {
    private Long subscriptionId;
    private String userId;
    private BigDecimal amount;
    private String frequency;
    private LocalDate nextRunDate;
    private String type; // e.g., "CREATED", "CANCELLED"
}