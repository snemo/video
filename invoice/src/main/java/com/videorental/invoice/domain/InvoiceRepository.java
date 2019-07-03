package com.videorental.invoice.domain;

import com.videorental.common.rental.OrderId;
import com.videorental.invoice.dto.InvoiceDto;

import java.util.Optional;

/**
 * @author oleciwoj
 */
public interface InvoiceRepository {

    void save(InvoiceIssued invoice);

    Optional<InvoiceDto> findByOrderId(OrderId orderId);
}
