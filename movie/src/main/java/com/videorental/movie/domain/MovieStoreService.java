package com.videorental.movie.domain;

import com.videorental.common.movie.MovieId;
import com.videorental.movie.MovieStore;
import com.videorental.movie.command.NewMovieCommand;
import com.videorental.movie.dto.MovieDetails;

import java.util.Optional;

/**
 * @author oleciwoj
 */
class MovieStoreService implements MovieStore {


    @Override
    public MovieId add(NewMovieCommand cmd) {
        return null;
    }

    @Override
    public Optional<MovieDetails> get(MovieId id) {
        return Optional.empty();
    }
}
