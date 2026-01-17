package com.bank.payments.subscription.service;

import com.bank.payments.events.SubscriptionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class SubscriptionProducer {

    private final KafkaTemplate<String, SubscriptionEvent> kafkaTemplate;

    public void sendEvent(SubscriptionEvent event) {
        log.info("Sending subscription event");

        kafkaTemplate.send("subscription-events", event);
    }
}
