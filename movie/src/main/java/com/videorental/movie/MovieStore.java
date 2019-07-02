package com.videorental.movie;

import com.videorental.common.movie.MovieId;
import com.videorental.movie.command.NewMovieCommand;
import com.videorental.movie.dto.MovieDetailsDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author oleciwoj
 */
public interface MovieStore {

    MovieId add(NewMovieCommand command);

    List<MovieDetailsDto> getAll(Set<MovieId> id);
}
