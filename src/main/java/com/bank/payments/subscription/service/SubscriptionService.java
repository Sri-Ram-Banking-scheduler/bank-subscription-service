package com.bank.payments.subscription.service;

import com.bank.payments.subscription.dto.SubscriptionRequest;
import com.bank.payments.subscription.event.SubscriptionEvent;
import com.bank.payments.subscription.model.Subscription;
import com.bank.payments.subscription.model.SubscriptionStatus;
import com.bank.payments.subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository repository;
    private final SubscriptionProducer producer;

    @Transactional
    public Subscription createSubscription(SubscriptionRequest request) {
        Subscription sub = Subscription.builder().userId(request.getUserId()).amount(request.getAmount()).frequency(request.getFrequency()).status(SubscriptionStatus.ACTIVE).build();

        Subscription saved = repository.save(sub);

        // 2. Publish Event
        SubscriptionEvent event = SubscriptionEvent.builder().subscriptionId(saved.getId()).userId(saved.getUserId()).amount(saved.getAmount()).frequency(saved.getFrequency().name()).type("CREATED").nextRunDate(saved.getNextRunDate()).build();

        producer.sendEvent(event);

        return mapToResponse(saved);
    }

    private Subscription mapToResponse(Subscription saved) {
        return Subscription.builder().userId(saved.getUserId()).amount(saved.getAmount()).frequency(saved.getFrequency()).nextRunDate(saved.getNextRunDate()).createdAt(saved.getCreatedAt()).status(saved.getStatus()).id(saved.getId()).build();
    }
}