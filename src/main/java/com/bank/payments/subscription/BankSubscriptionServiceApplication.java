package com.bank.payments.subscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BankSubscriptionServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(BankSubscriptionServiceApplication.class, args);
    }

}
