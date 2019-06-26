package com.videorental.customer.command;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

/**
 * @author oleciwoj
 */
@Value
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CreateCustomerCommand {
    String firstName;
    String lastName;
}
