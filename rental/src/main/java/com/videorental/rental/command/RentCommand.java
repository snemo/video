package com.videorental.rental.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.videorental.common.customer.CustomerId;
import com.videorental.common.movie.MovieId;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.List;

/**
 * @author oleciwoj
 */
@Value
@Builder
public class RentCommand {
    private final CustomerId customer;
    private final List<Item> movies;

    public RentCommand(@JsonProperty("customer") CustomerId customer,
                       @JsonProperty("movies") List<Item> movies) {
        this.customer = customer;
        this.movies = movies;
    }

    @Getter
    public static class Item {
        private final MovieId movieId;
        private final int days;

        public Item(@JsonProperty("movieId") MovieId movieId,
                    @JsonProperty("days") int days) {
            this.movieId = movieId;
            this.days = days;
        }
    }

}
