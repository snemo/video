package com.videorental.movie.domain;

import com.videorental.common.movie.MovieId;
import com.videorental.movie.command.NewMovieCommand;
import com.videorental.movie.dto.MovieDetails;

import java.util.Optional;

/**
 * @author oleciwoj
 */
interface MovieRepository {

    MovieId add(NewMovieCommand cmd);

    Optional<MovieDetails> get(MovieId id);
}
