package com.videorental.invoice.infrastructure

import com.videorental.common.customer.CustomerId
import com.videorental.common.invoice.InvoiceId
import com.videorental.common.movie.MovieId
import com.videorental.common.pricing.Money
import com.videorental.common.rental.OrderId
import com.videorental.db.config.LiquibaseDataSourceHolder
import com.videorental.invoice.domain.InvoiceIssued
import com.videorental.invoice.domain.InvoiceRepository
import spock.lang.Shared
import spock.lang.Specification

/**
 *
 * @author oleciwoj
 */
class InvoiceRepositoryImplInt extends Specification {

    @Shared InvoiceRepository repository

    def setupSpec() {
        repository = new InvoiceRepositoryImpl(LiquibaseDataSourceHolder.getDSLContext())
    }

    def "add a new invoice to DB"() {
        given:
            def invoice = dummyInvoice()

        when:
            repository.save(invoice)

        then:
            noExceptionThrown()
    }

    def "find invoice in DB by order ID"() {
        given:
            def invoiceIssued = dummyInvoice()
            repository.save(invoiceIssued)

        when:
            def invoice = repository.findByOrderId(invoiceIssued.orderId).get()

        then:
            invoice.order == invoiceIssued.orderId
            invoice.customer == invoiceIssued.customerId
            invoice.totalPrice == Money.of(70)
    }

    InvoiceIssued dummyInvoice() {
        InvoiceIssued.builder()
                .id(InvoiceId.generate())
                .orderId(OrderId.generate())
                .customerId(CustomerId.generate())
                .rows([
                        new InvoiceIssued.Row(MovieId.generate(), Money.of(40)),
                        new InvoiceIssued.Row(MovieId.generate(), Money.of(30))
                ])
                .build()
    }
}
