package com.videorental.movie;

import com.videorental.common.movie.MovieId;
import com.videorental.movie.command.NewMovieCommand;
import com.videorental.movie.dto.MovieDetails;

import java.util.Optional;

/**
 * @author oleciwoj
 */
public interface MovieStore {

    MovieId add(NewMovieCommand cmd);

    Optional<MovieDetails> get(MovieId id);
}
