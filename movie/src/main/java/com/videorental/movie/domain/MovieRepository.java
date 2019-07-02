package com.videorental.movie.domain;

import com.videorental.common.movie.MovieId;
import com.videorental.movie.command.NewMovieCommand;
import com.videorental.movie.dto.MovieDetailsDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author oleciwoj
 */
public interface MovieRepository {

    MovieId save(NewMovieCommand cmd);

    List<MovieDetailsDto> findAllById(Set<MovieId> ids);
}
