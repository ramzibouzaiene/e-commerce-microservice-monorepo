package com.ramzibz.kafka;

import com.ramzibz.customer.CustomerResponse;
import com.ramzibz.order.PaymentMethod;
import com.ramzibz.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
