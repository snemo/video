package com.videorental.common.rental;

import com.videorental.common.customer.CustomerId;
import com.videorental.common.movie.MovieId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

/**
 * @author oleciwoj
 */
public class MovieReturnedEvent extends ApplicationEvent {

    private final OrderId order;
    private final CustomerId customer;
    private final List<ReturnedMovie> movies;

    @Builder
    public MovieReturnedEvent(Object source, OrderId order, CustomerId customer, List<ReturnedMovie> movies) {
        super(source);
        this.order = order;
        this.customer = customer;
        this.movies = movies;
    }

    @Getter
    @AllArgsConstructor
    public static class ReturnedMovie {
        private final MovieId movieId;
        private final int extraDays;
    }

    public OrderId getOrder() {
        return order;
    }

    public CustomerId getCustomer() {
        return customer;
    }

    public List<ReturnedMovie> getMovies() {
        return movies;
    }
}
