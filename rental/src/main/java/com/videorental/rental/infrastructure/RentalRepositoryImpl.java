package com.videorental.rental.infrastructure;

import com.videorental.common.rental.OrderId;
import com.videorental.rental.command.RentCommand;
import com.videorental.rental.domain.RentalRepository;
import com.videorental.rental.exception.RentalException;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.videorental.db.generated.tables.Orders.ORDERS;

/**
 * @author oleciwoj
 */
@Repository
@AllArgsConstructor
class RentalRepositoryImpl implements RentalRepository {

    private final DSLContext dsl;

    @Override
    public OrderId save(RentCommand command) {
        var orderId = OrderId.generate();

        var rows = dsl.insertInto(ORDERS)
                .set(ORDERS.ID, orderId.id())
                .set(ORDERS.CUSTOMER_ID, command.getCustomer().id())
                .set(ORDERS.CREATED, LocalDateTime.now())
                .execute();
        if (rows != 1)
            throw new RentalException("Could not write new Order into DB: " + command);

        return orderId;
    }
}
