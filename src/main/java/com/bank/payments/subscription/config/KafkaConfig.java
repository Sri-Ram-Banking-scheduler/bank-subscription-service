package com.bank.payments.subscription.config;

import com.bank.payments.subscription.event.SubscriptionEvent;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, SubscriptionEvent> producerFactory(ObjectMapper objectMapper) {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.springframework.kafka.support.serializer.JsonSerializer");

        // ✅ Spring Kafka 4.x configuration keys
        props.put("spring.json.value.default.type",
                "com.bank.payments.subscription.event.SubscriptionEvent");
//        props.put(., true);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, SubscriptionEvent> kafkaTemplate(
            ProducerFactory<String, SubscriptionEvent> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
