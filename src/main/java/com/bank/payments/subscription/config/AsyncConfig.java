package com.bank.payments.subscription.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        // SimpleAsyncTaskExecutor in Java 21+ can be backed by Virtual Threads
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        
        // Use Virtual Threads instead of Platform Threads
        executor.setVirtualThreads(true);
        executor.setThreadNamePrefix("audit-vt-");

        // CRITICAL: Trace ID propagation
        executor.setTaskDecorator(runnable -> {
            io.micrometer.context.ContextSnapshot snapshot = 
                io.micrometer.context.ContextSnapshot.captureAll();
            return () -> {
                try (io.micrometer.context.ContextSnapshot.Scope scope = snapshot.setThreadLocals()) {
                    runnable.run();
                }
            };
        });

        return executor;
    }
}