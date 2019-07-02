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
public class ReturnCommand {
    private final CustomerId customer;
    private final List<ReturnedMovie> movies;

    public ReturnCommand(@JsonProperty("customer") CustomerId customer,
                         @JsonProperty("movies") List<ReturnedMovie> movies) {
        this.customer = customer;
        this.movies = movies;
    }

    @Getter
    public static class ReturnedMovie {
        private final MovieId movieId;
        private final int extraDays;

        public ReturnedMovie(@JsonProperty("movieId") MovieId movieId,
                             @JsonProperty("extraDays") int extraDays) {
            this.movieId = movieId;
            this.extraDays = extraDays;
        }
    }
}
