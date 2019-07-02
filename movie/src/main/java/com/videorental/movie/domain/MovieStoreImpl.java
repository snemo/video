package com.videorental.movie.domain;

import com.videorental.common.movie.MovieId;
import com.videorental.movie.MovieStore;
import com.videorental.movie.command.NewMovieCommand;
import com.videorental.movie.dto.MovieDetailsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author oleciwoj
 */
@Service
@AllArgsConstructor
class MovieStoreImpl implements MovieStore {

    private final MovieRepository repository;

    @Override
    @Transactional
    public MovieId add(NewMovieCommand command) {
        return repository.save(command);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDetailsDto> getAll(Set<MovieId> ids) {
        return repository.findAllById(ids);
    }
}
