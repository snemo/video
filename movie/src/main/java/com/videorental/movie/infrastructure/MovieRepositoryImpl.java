package com.videorental.movie.infrastructure;

import com.videorental.common.movie.MovieId;
import com.videorental.movie.command.NewMovieCommand;
import com.videorental.movie.domain.MovieRepository;
import com.videorental.movie.dto.MovieDetailsDto;
import com.videorental.movie.exception.MovieException;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.videorental.db.generated.tables.Movies.MOVIES;

/**
 * @author oleciwoj
 */
@Repository
@AllArgsConstructor
class MovieRepositoryImpl implements MovieRepository {

    private final DSLContext dsl;

    @Override
    public MovieId save(NewMovieCommand command) {
        var movieId = MovieId.generate();

        var rows = dsl.insertInto(MOVIES)
                .set(MOVIES.ID, movieId.id())
                .set(MOVIES.TITLE, command.getTitle())
                .set(MOVIES.CATEGORY, command.getCategory().id())
                .execute();
        if (rows != 1)
            throw new MovieException("Could not write new Movie into DB: " + command);

        return movieId;
    }

    @Override
    public List<MovieDetailsDto> findAllById(Set<MovieId> ids) {
        var movies = dsl.selectFrom(MOVIES)
                .where(MOVIES.ID.in(ids.stream().map(MovieId::id).collect(Collectors.toList())))
                .fetchStream()
                .map(MovieDetailsDto::of)
                .collect(Collectors.toList());

        checkArgument(movies.size() == ids.size(), "Some of the movie was not found");
        return movies;
    }
}
