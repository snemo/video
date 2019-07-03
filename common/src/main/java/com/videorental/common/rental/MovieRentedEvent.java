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
public class MovieRentedEvent extends ApplicationEvent {

    private final OrderId order;
    private final CustomerId customer;
    private final List<MovieItem> movies;

    @Builder
    public MovieRentedEvent(Object source, OrderId order, CustomerId customer, List<MovieItem> movies) {
        super(source);
        this.order = order;
        this.customer = customer;
        this.movies = movies;
    }

    @Getter
    @AllArgsConstructor
    public static class MovieItem {
        private final MovieId movieId;
        private final int days;
    }

    public OrderId getOrder() {
        return order;
    }

    public CustomerId getCustomer() {
        return customer;
    }

    public List<MovieItem> getMovies() {
        return movies;
    }
}
