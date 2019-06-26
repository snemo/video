package com.videorental.customer;

import com.videorental.common.customer.CustomerId;
import com.videorental.customer.command.CreateCustomerCommand;
import com.videorental.customer.dto.CustomerDetails;

import java.util.Optional;

/**
 * @author oleciwoj
 */
public interface CustomerStore {

    CustomerId add(CreateCustomerCommand command);

    Optional<CustomerDetails> get(CustomerId id);
}
