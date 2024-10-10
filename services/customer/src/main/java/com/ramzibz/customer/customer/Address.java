package com.ramzibz.customer.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
@Getter
@Setter
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}
