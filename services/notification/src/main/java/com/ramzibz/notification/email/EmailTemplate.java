package com.ramzibz.notification.email;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailTemplate {
    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment Successfully Processed"),
    ORDER_CONFIRMATION("order-confirmation.html", "Order Confirmation");

    private final String template;
    private final String subject;
}
