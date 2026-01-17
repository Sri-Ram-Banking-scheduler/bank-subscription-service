//package com.bank.payments.subscription.config;
//
//import com.bank.payments.events.SubscriptionEvent;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
//import java.util.Map;
//
//@Configuration
//public class KafkaConfig {
//
//    @Bean
//    public ProducerFactory<String, SubscriptionEvent> producerFactory(KafkaProperties kafkaProperties) {
//        // Build properties from YAML automatically
//        Map<String, Object> props = kafkaProperties.buildProducerProperties(null);
//        return new DefaultKafkaProducerFactory<>(props);
//    }
//
//    @Bean
//    public KafkaTemplate<String, SubscriptionEvent> kafkaTemplate(
//            ProducerFactory<String, SubscriptionEvent> producerFactory) {
//        return new KafkaTemplate<>(producerFactory);
//    }
//}