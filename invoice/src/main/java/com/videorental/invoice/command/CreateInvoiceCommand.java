package com.videorental.invoice.command;

import com.videorental.common.customer.CustomerId;
import com.videorental.common.movie.MovieId;
import com.videorental.common.rental.OrderId;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Set;

/**
 * @author oleciwoj
 */
@Value
@Builder
public class CreateInvoiceCommand {

    private final OrderId order;
    private final CustomerId customer;
    private final Set<MovieId> movies;

}
