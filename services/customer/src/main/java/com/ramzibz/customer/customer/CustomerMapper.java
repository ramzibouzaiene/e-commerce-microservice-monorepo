package com.ramzibz.customer.customer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerRequest request);
    CustomerRequest toDto(Customer customer);
    List<CustomerResponse> responseToDto(List<Customer> customer);
    @Mapping(target = "id", ignore = true)
    void updateCustomerFromRequest(CustomerRequest request, @MappingTarget Customer customer);
}
