package com.videorental.invoice;

import com.videorental.common.rental.OrderId;
import com.videorental.invoice.dto.InvoiceDto;

import java.util.Optional;

/**
 * @author oleciwoj
 */
public interface Invoicing {

   Optional<InvoiceDto> findByOrderId(OrderId orderId);

}
