package com.videorental.rental.domain;

import com.videorental.common.rental.MovieRentedEvent;
import com.videorental.common.rental.MovieReturnedEvent;
import com.videorental.common.rental.OrderId;
import com.videorental.rental.Rental;
import com.videorental.rental.command.RentCommand;
import com.videorental.rental.command.ReturnCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

/**
 * @author oleciwoj
 */
@Slf4j
@Service
@AllArgsConstructor
class RentalService implements Rental {

    private final ApplicationEventPublisher eventPublisher;
    private final RentalRepository repository;

    @Override
    @Transactional
    public OrderId rentMovie(RentCommand command) {
        var orderId = repository.save(command);

        eventPublisher.publishEvent("");

        eventPublisher.publishEvent(MovieRentedEvent.builder()
                .source(this)
                .order(orderId)
                .customer(command.getCustomer())
                .movies(command.getMovies().stream()
                        .map(item -> new MovieRentedEvent.MovieItem(item.getMovieId(), item.getDays()))
                        .collect(Collectors.toList()))
                .build());

        return orderId;
    }

    @Override
    public OrderId returnMovie(ReturnCommand command) {
        var orderId = OrderId.generate();

        eventPublisher.publishEvent(MovieReturnedEvent.builder()
                .source(this)
                .order(orderId)
                .customer(command.getCustomer())
                .movies(command.getMovies().stream()
                        .map(item -> new MovieReturnedEvent.ReturnedMovie(item.getMovieId(), item.getExtraDays()))
                        .collect(Collectors.toList()))
                .build());

        return orderId;
    }
}
