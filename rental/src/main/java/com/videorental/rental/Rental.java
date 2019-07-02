package com.videorental.rental;

import com.videorental.common.rental.OrderId;
import com.videorental.rental.command.RentCommand;
import com.videorental.rental.command.ReturnCommand;
import com.videorental.rental.dto.RentSummary;

/**
 * @author oleciwoj
 */
public interface Rental {

    OrderId rentMovie(RentCommand command);

    OrderId returnMovie(ReturnCommand command);
}
