package com.bank.payments.subscription.model;

public enum SubscriptionStatus {
    ACTIVE,     // Payments are scheduled
    PAUSED,     // User temporarily stopped payments
    CANCELLED,  // Permanently stopped
    FAILED      // System stopped it due to repeated payment failures
}