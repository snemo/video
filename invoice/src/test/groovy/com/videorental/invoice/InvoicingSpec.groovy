package com.videorental.invoice

import com.videorental.common.customer.CustomerId
import com.videorental.common.movie.MovieCategory
import com.videorental.common.movie.MovieId
import com.videorental.common.pricing.Money
import com.videorental.common.rental.MovieRentedEvent
import com.videorental.common.rental.MovieReturnedEvent
import com.videorental.common.rental.OrderId
import com.videorental.invoice.domain.InvoiceFactory
import com.videorental.invoice.domain.InvoiceService
import com.videorental.invoice.infrastructure.InvoiceRepositoryStub
import com.videorental.mocks.movie.MovieStoreMock
import com.videorental.mocks.pricing.PricingMock
import com.videorental.movie.command.NewMovieCommand
import spock.lang.Shared
import spock.lang.Specification

/**
 *
 * @author oleciwoj
 */
class InvoicingSpec extends Specification {

    @Shared MovieStoreMock movieStore
    @Shared Invoicing invoicing

    def setupSpec() {
        movieStore = new MovieStoreMock()
        def invoiceFactory = new InvoiceFactory(movieStore, new PricingMock())
        invoicing = new InvoiceService(new InvoiceRepositoryStub(), invoiceFactory)
    }

    def "get rent invoice by order ID"() {
        given:
            def orderId = OrderId.generate()
            def customerId = CustomerId.generate()
            def movieId = movieStore.add(new NewMovieCommand("Matrix 11", MovieCategory.NEW_RELEASE));

            invoicing.on(rentedEvent(orderId, customerId, movieId, 2))

        when:
            def invoice = invoicing.findByOrderId(orderId).get()

        then:
            invoice != null
            invoice.customer == customerId
            invoice.order == orderId
            invoice.totalPrice == Money.of(80)
    }

    def "get surcharge - return invoice by order ID"() {
        given:
            def orderId = OrderId.generate()
            def customerId = CustomerId.generate()
            def movieId = movieStore.add(new NewMovieCommand("Spider Man", MovieCategory.REGULAR));

            invoicing.on(returnedEvent(orderId, customerId, movieId, 2))

        when:
            def invoice = invoicing.findByOrderId(orderId).get()

        then:
            invoice != null
            invoice.customer == customerId
            invoice.order == orderId
            invoice.totalPrice == Money.of(60)
    }

    MovieRentedEvent rentedEvent(OrderId orderId, CustomerId customerId, MovieId movieId, int days) {
        MovieRentedEvent.builder()
                .source(this)
                .customer(customerId)
                .order(orderId)
                .movies([new MovieRentedEvent.MovieItem(movieId, days)])
                .build()
    }

    MovieReturnedEvent returnedEvent(OrderId orderId, CustomerId customerId, MovieId movieId, int days) {
        MovieReturnedEvent.builder()
                .source(this)
                .customer(customerId)
                .order(orderId)
                .movies([new MovieReturnedEvent.ReturnedMovie(movieId, days)])
                .build()
    }

}
