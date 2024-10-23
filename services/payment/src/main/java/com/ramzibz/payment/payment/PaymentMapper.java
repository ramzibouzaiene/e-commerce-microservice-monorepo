package com.ramzibz.payment.payment;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment toRequest(PaymentRequest paymentRequest);
}
