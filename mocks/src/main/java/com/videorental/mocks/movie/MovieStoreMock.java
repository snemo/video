package com.videorental.mocks.movie;

import com.videorental.common.movie.MovieId;
import com.videorental.movie.MovieStore;
import com.videorental.movie.command.NewMovieCommand;
import com.videorental.movie.dto.MovieDetailsDto;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author oleciwoj
 */
public class MovieStoreMock implements MovieStore {

    private Map<MovieId, MovieDetailsDto> store = new HashMap<>();

    @Override
    public MovieId add(NewMovieCommand command) {
        var movieId = MovieId.generate();
        store.put(movieId, toDetails(movieId, command));
        return movieId;
    }

    @Override
    public List<MovieDetailsDto> getAll(Set<MovieId> ids) {
        return ids.stream()
                .map(store::get)
                .collect(Collectors.toList());
    }

    public void reset() {
        store.clear();
    }

    private MovieDetailsDto toDetails(MovieId movieId, NewMovieCommand command) {
        return MovieDetailsDto.builder()
                .id(movieId)
                .title(command.getTitle())
                .category(command.getCategory())
                .build();
    }
}
