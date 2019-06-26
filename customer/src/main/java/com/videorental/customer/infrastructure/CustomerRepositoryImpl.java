package com.videorental.customer.infrastructure;

import com.videorental.common.customer.CustomerId;
import com.videorental.common.exception.ErrorCode;
import com.videorental.customer.command.CreateCustomerCommand;
import com.videorental.customer.domain.CustomerException;
import com.videorental.customer.dto.CustomerDetails;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.videorental.db.generated.tables.Customers.CUSTOMERS;


/**
 * @author oleciwoj
 */
@Repository
@AllArgsConstructor
class CustomerRepositoryImpl implements CustomerRepository {

    private final DSLContext dsl;

    public CustomerId save(CreateCustomerCommand command) {
        var id = CustomerId.generate();

        var rows = dsl.insertInto(CUSTOMERS)
                .set(CUSTOMERS.ID, id.id())
                .set(CUSTOMERS.FIRSTNAME, command.getFirstName())
                .set(CUSTOMERS.LASTNAME, command.getLastName())
                .execute();

        if (rows != 1)
            throw new CustomerException(ErrorCode.DB, "Could not write new Customer into DB: " + id);

        return id;
    }

    public Optional<CustomerDetails> get(CustomerId id) {
        return dsl.selectFrom(CUSTOMERS)
                .where(CUSTOMERS.ID.eq(id.id()))
                .fetchStream()
                .findAny()
                .map(CustomerDetails::of);
    }

}
