package com.videorental.invoice.infrastructure

import com.videorental.common.rental.OrderId
import com.videorental.invoice.domain.InvoiceIssued
import com.videorental.invoice.domain.InvoiceRepository
import com.videorental.invoice.dto.InvoiceDto

/**
 *
 * @author oleciwoj
 */
class InvoiceRepositoryStub implements InvoiceRepository {

    Map<OrderId, InvoiceDto> store = [:]

    @Override
    void save(InvoiceIssued invoice) {
        store.put(invoice.orderId, toInvoice(invoice))
    }

    @Override
    Optional<InvoiceDto> findByOrderId(OrderId orderId) {
        Optional.ofNullable(store.get(orderId))
    }

    InvoiceDto toInvoice(InvoiceIssued issued) {
        InvoiceDto.builder()
                .order(issued.orderId)
                .customer(issued.customerId)
                .totalPrice(issued.totalPrice())
                .build()
    }
}
