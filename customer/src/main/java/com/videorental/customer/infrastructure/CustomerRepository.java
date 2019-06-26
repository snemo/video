package com.videorental.customer.infrastructure;

import com.videorental.common.customer.CustomerId;
import com.videorental.customer.command.CreateCustomerCommand;
import com.videorental.customer.dto.CustomerDetails;

import java.util.Optional;

/**
 * @author oleciwoj
 */
public interface CustomerRepository {

    CustomerId save(CreateCustomerCommand command);

    Optional<CustomerDetails> get(CustomerId id);
}
