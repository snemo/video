package com.videorental.customer.dto;

import com.videorental.common.customer.CustomerId;
import com.videorental.db.generated.tables.records.CustomersRecord;
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
public class CustomerDetails {
    CustomerId id;
    String firstName;
    String lastName;

    public static CustomerDetails of(CustomersRecord record) {
        return builder()
                .id(CustomerId.of(record.getId()))
                .firstName(record.getFirstname())
                .lastName(record.getLastname())
                .build();
    }
}
