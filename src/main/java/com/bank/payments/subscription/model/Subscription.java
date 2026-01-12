package com.bank.payments.subscription.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Frequency frequency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionStatus status;

    @Column(nullable = false)
    private LocalDate nextRunDate;

    @Version
    private Long version; // Optimistic locking for concurrency

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    public void calculateFirstRun() {
      calculateNextRun();
    }

    public void calculateNextRun() {
        // If it's a new subscription, start from today
        LocalDate baseDate = (this.nextRunDate == null) ? LocalDate.now() : this.nextRunDate;

        this.nextRunDate = switch (this.frequency) {
            case HOURLY  -> baseDate; // For hourly, the "date" remains today
            case DAILY   -> baseDate.plusDays(1);
            case WEEKLY  -> baseDate.plusWeeks(1);
            case MONTHLY -> baseDate.plusMonths(1);
        };
    }
}