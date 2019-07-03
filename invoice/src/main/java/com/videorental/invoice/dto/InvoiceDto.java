package com.videorental.invoice.dto;

import com.videorental.common.customer.CustomerId;
import com.videorental.common.movie.MovieId;
import com.videorental.common.pricing.Money;
import com.videorental.common.rental.OrderId;
import com.videorental.db.generated.tables.records.InvoicesRecord;
import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * @author oleciwoj
 */
@Value
@Builder
public class InvoiceDto {

    private final OrderId order;
    private final CustomerId customer;
    private final List<InvoiceItem> items;
    private final Money totalPrice;

    @Value
    public static class InvoiceItem {
        private final MovieId movie;
        private final Money price;
    }

    public static InvoiceDto of(InvoicesRecord record) {
        return builder()
                .order(OrderId.of(record.getOrderId()))
                .customer(CustomerId.of(record.getCustomerId()))
                .totalPrice(Money.of(record.getTotalPrice()))
                .build();
    }
}
