package com.ramzibz.payment.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "First Name is required")
        String firstName,
        @NotNull(message = "Last Name is required")
        String lastName,
        @NotNull(message = "Email is required")
        @Email(message = "Customer is not correctly formatted")
        String email
) {
}