package com.videorental.invoice.domain;

import com.videorental.common.rental.MovieRentedEvent;
import com.videorental.common.rental.MovieReturnedEvent;
import com.videorental.common.rental.OrderId;
import com.videorental.invoice.Invoicing;
import com.videorental.invoice.dto.InvoiceDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author oleciwoj
 */
@Slf4j
@Service
@AllArgsConstructor
class InvoiceService implements Invoicing  {

    private final InvoiceRepository repository;
    private final InvoiceFactory invoiceFactory;

    @EventListener
    @Transactional
    public void on(MovieRentedEvent event) {
        var invoice = invoiceFactory.build(event);
        repository.save(invoice);
    }

    @EventListener
    @Transactional
    public void on(MovieReturnedEvent event) {
        var invoice = invoiceFactory.build(event);
        repository.save(invoice);
    }

    @Override
    @Transactional
    public Optional<InvoiceDto> findByOrderId(OrderId orderId) {
        return repository.findByOrderId(orderId);
    }
}
