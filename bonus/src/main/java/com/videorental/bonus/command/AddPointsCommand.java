package com.videorental.bonus.command;

import com.videorental.common.customer.CustomerId;
import com.videorental.common.movie.MovieId;
import com.videorental.common.rental.OrderId;
import lombok.Builder;
import lombok.Value;

/**
 * @author oleciwoj
 */
@Value
@Builder
public class AddPointsCommand {
    private final CustomerId customerId;
    private final int points;
    private final OrderId orderId;
    private final MovieId movieId;
}
