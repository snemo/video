package com.videorental.movie.infrastructure

import com.videorental.common.movie.MovieId
import com.videorental.movie.command.NewMovieCommand
import com.videorental.movie.domain.MovieRepository
import com.videorental.movie.dto.MovieDetailsDto

import java.util.stream.Collectors

/**
 *
 * @author oleciwoj
 */
class MovieRepositoryStub implements MovieRepository {

    Map<MovieId, MovieDetailsDto> store = [:]

    @Override
    MovieId save(NewMovieCommand command) {
        def movieId = MovieId.generate()
        store.put(movieId, transform(movieId, command))
        return movieId
    }

    @Override
    List<MovieDetailsDto> findAllById(Set<MovieId> ids) {
        return ids.stream()
                .map { store.get(it) }
                .collect(Collectors.toList())
    }

    MovieDetailsDto transform(MovieId movieId, NewMovieCommand command) {
        MovieDetailsDto.builder()
                .id(movieId)
                .title(command.title)
                .category(command.category)
                .build()
    }
}
