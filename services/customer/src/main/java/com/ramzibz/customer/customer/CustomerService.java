package com.ramzibz.customer.customer;

import com.ramzibz.customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toEntity(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with the provided ID:: %s", request.id())
                ));
        mapper.updateCustomerFromRequest(request, customer);
        repository.save(customer);
    }

    public List<CustomerResponse> findAllCustomers() {
        List<Customer> customers = repository.findAll();
        return mapper.responseToDto(customers);
    }

    public Boolean getCustomerById(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    public void deleteCustomer(String customerId) {
        Boolean customerExist = this.getCustomerById(customerId);
        if (customerExist != null && customerExist) {
            repository.deleteById(customerId);
        }
        throw new CustomerNotFoundException("Customer Not Found");
    }
}
