package com.videorental.customer.application;

import com.videorental.common.customer.CustomerId;
import com.videorental.customer.CustomerStore;
import com.videorental.customer.command.CreateCustomerCommand;
import com.videorental.customer.dto.CustomerDetails;
import com.videorental.customer.infrastructure.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author oleciwoj
 */
@Service
@AllArgsConstructor
class CustomerStoreService implements CustomerStore {

    private final CustomerRepository repository;

    @Override
    public CustomerId add(CreateCustomerCommand command) {
        return repository.save(command);
    }

    @Override
    public Optional<CustomerDetails> get(CustomerId id) {
        return repository.get(id);
    }
}
