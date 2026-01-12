package com.bank.payments.subscription.dto;

import com.bank.payments.subscription.model.Frequency;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SubscriptionRequest {

    @NotBlank(message = "User ID is required")
    private String userId;

    @Positive(message = "Amount must be greater than zero")
    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotNull(message = "Frequency is required")
    private Frequency frequency;
}