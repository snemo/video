package com.videorental.invoice.domain;

import com.videorental.common.customer.CustomerId;
import com.videorental.common.invoice.InvoiceId;
import com.videorental.common.movie.MovieId;
import com.videorental.common.pricing.Money;
import com.videorental.common.rental.OrderId;
import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * @author oleciwoj
 */
@Value
@Builder
public class InvoiceIssued {

    private final InvoiceId id;
    private final OrderId orderId;
    private final CustomerId customerId;
    private final List<Row> rows;

    @Value
    static class Row {
        private final MovieId movieId;
        private final Money price;
    }

    public Money totalPrice() {
        return rows.stream()
                .map(Row::getPrice)
                .reduce(Money.ZERO, Money::add);
    }
}
