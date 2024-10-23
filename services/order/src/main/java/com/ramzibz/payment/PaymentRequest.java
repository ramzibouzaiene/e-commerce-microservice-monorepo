package com.ramzibz.payment;

import com.ramzibz.customer.CustomerResponse;
import com.ramzibz.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
