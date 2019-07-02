package com.videorental.movie.infrastructure

import com.videorental.common.movie.MovieCategory
import com.videorental.db.config.LiquibaseDataSourceHolder
import com.videorental.movie.command.NewMovieCommand
import com.videorental.movie.domain.MovieRepository
import spock.lang.Shared
import spock.lang.Specification

/**
 *
 * @author oleciwoj
 */
class MovieRepositoryImplInt extends Specification {

    @Shared MovieRepository repository

    def setupSpec() {
        repository = new MovieRepositoryImpl(LiquibaseDataSourceHolder.getDSLContext())
    }

    def "save a new movie into database"() {
        given:
            def command = new NewMovieCommand("Matrix 11", MovieCategory.NEW_RELEASE)

        when:
            def result = repository.save(command)

        then:
            result != null
            result.id() != null
    }

    def "find all movies by id list"() {
        given:
            Set ids = []
            ids << repository.save(new NewMovieCommand("Matrix 11", MovieCategory.NEW_RELEASE))
            ids << repository.save(new NewMovieCommand("Spider Man", MovieCategory.REGULAR))

        when:
            def result = repository.findAllById(ids)

        then:
            result.size() == 2
            result.title.sort() == ["Matrix 11", "Spider Man"].sort()
            result.category.sort() == [MovieCategory.NEW_RELEASE, MovieCategory.REGULAR].sort()
            result.id.containsAll(ids)
    }
}
