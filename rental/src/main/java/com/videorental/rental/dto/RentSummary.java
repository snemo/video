package com.videorental.rental.dto;

import com.videorental.common.customer.CustomerId;
import com.videorental.common.movie.MovieId;
import com.videorental.common.pricing.Money;
import com.videorental.common.rental.OrderId;
import lombok.Value;

import java.util.List;

/**
 * @author oleciwoj
 */
@Value
public class RentSummary {
    private final OrderId order;
    private final CustomerId customer;
    private final List<Item> items;
    private final Money totalPrice;

    @Value
    public static class Item {
        private final MovieId movie;
        private final Money price;
    }
}
