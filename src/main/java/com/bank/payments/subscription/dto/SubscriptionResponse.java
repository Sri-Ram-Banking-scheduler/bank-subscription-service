package com.bank.payments.subscription.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class SubscriptionResponse {
    private Long id;
    private String userId;
    private BigDecimal amount;
    private String frequency;
    private String status;
    private LocalDateTime nextRunDate;
    private LocalDateTime createdDate;
}