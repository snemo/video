package com.videorental.invoice.domain;

import com.videorental.common.invoice.InvoiceId;
import com.videorental.common.movie.MovieId;
import com.videorental.common.pricing.Money;
import com.videorental.common.rental.MovieRentedEvent;
import com.videorental.common.rental.MovieReturnedEvent;
import com.videorental.movie.MovieStore;
import com.videorental.movie.dto.MovieDetailsDto;
import com.videorental.pricing.Pricing;
import com.videorental.pricing.command.CalculatePriceCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author oleciwoj
 */
@Component
@AllArgsConstructor
class InvoiceFactory {

    private final MovieStore movieStore;
    private final Pricing pricing;

    InvoiceIssued build(MovieRentedEvent event) {
        var invoiceId = InvoiceId.generate();

        var movies = getMovies(event.getMovies().stream()
                .map(MovieRentedEvent.MovieItem::getMovieId).collect(Collectors.toSet()));

        var invoiceRows = event.getMovies().stream()
                .map(rentItem -> new InvoiceIssued.Row(rentItem.getMovieId(),
                        rentPrice(movies.get(rentItem.getMovieId()), rentItem.getDays())))
                .collect(Collectors.toList());

        return InvoiceIssued.builder()
                .id(invoiceId)
                .orderId(event.getOrder())
                .customerId(event.getCustomer())
                .rows(invoiceRows)
                .build();
    }

    InvoiceIssued build(MovieReturnedEvent event) {
        var invoiceId = InvoiceId.generate();

        var movies = getMovies(event.getMovies().stream()
                .map(MovieReturnedEvent.ReturnedMovie::getMovieId).collect(Collectors.toSet()));

        var invoiceRows = event.getMovies().stream()
                .map(returnedMovie -> new InvoiceIssued.Row(returnedMovie.getMovieId(),
                        surchargePrice(movies.get(returnedMovie.getMovieId()), returnedMovie.getExtraDays())))
                .collect(Collectors.toList());

        return InvoiceIssued.builder()
                .id(invoiceId)
                .orderId(event.getOrder())
                .customerId(event.getCustomer())
                .rows(invoiceRows)
                .build();
    }

    private Money rentPrice(MovieDetailsDto movie, int days) {
        return pricing.rent(CalculatePriceCommand.builder()
                .movieCategory(movie.getCategory())
                .days(days)
                .build());
    }

    private Money surchargePrice(MovieDetailsDto movie, int days) {
        return pricing.surcharge(CalculatePriceCommand.builder()
                .movieCategory(movie.getCategory())
                .days(days)
                .build());
    }

    private Map<MovieId, MovieDetailsDto> getMovies(Set<MovieId> ids) {
        return movieStore.getAll(ids).stream()
                .collect(Collectors.toMap(MovieDetailsDto::getId, m -> m));
    }
}
