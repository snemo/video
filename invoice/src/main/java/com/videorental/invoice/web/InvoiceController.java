package com.videorental.invoice.web;

import com.videorental.common.rental.OrderId;
import com.videorental.invoice.Invoicing;
import com.videorental.invoice.dto.InvoiceDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author oleciwoj
 */
@RestController
@RequestMapping("/api/invoice")
@AllArgsConstructor
class InvoiceController {

    private final Invoicing invoicing;

    @GetMapping("/order/{orderId}")
    Optional<InvoiceDto> get(@PathVariable String orderId) {
        return invoicing.findByOrderId(OrderId.of(orderId));
    }
}
