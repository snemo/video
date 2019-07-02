package com.videorental.rental.infrastructure

import com.videorental.common.rental.OrderId
import com.videorental.rental.command.RentCommand
import com.videorental.rental.domain.RentalRepository

/**
 *
 * @author oleciwoj
 */
class RentalRepositoryStub implements RentalRepository {
    @Override
    OrderId save(RentCommand command) {
        OrderId.generate()
    }
}
