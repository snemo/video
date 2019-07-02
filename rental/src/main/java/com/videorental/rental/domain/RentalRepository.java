package com.videorental.rental.domain;

import com.videorental.common.rental.OrderId;
import com.videorental.rental.command.RentCommand;

/**
 * @author oleciwoj
 */
public interface RentalRepository {

    OrderId save(RentCommand command);
}
