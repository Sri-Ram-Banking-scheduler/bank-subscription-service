package com.bank.payments.subscription.repository;

import com.bank.payments.subscription.model.Subscription;
import com.bank.payments.subscription.model.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    // This will be used by our Scheduler later to find who to pay
    List<Subscription> findAllByStatusAndNextRunDateBefore(SubscriptionStatus status, LocalDateTime dateTime);
}