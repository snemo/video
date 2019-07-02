package com.videorental.movie

import com.videorental.common.movie.MovieCategory
import com.videorental.movie.command.NewMovieCommand
import com.videorental.movie.domain.MovieStoreImpl
import com.videorental.movie.infrastructure.MovieRepositoryStub
import spock.lang.Shared
import spock.lang.Specification

/**
 * This is a main facade test of Movie module
 *
 * @author oleciwoj
 */
class MovieStoreSpec extends Specification {

    @Shared MovieStore movieStore

    def setupSpec() {
        movieStore = new MovieStoreImpl(new MovieRepositoryStub())
    }

    def "add new movie into stock"() {
        given:
            def command = command("Matrix 11", MovieCategory.NEW_RELEASE)

        when:
            def result = movieStore.add(command)

        then:
            result != null
            result.id() != null
    }

    def "get all movies by its id"() {
        given:
            Set ids = []
            ids << movieStore.add(command("Matrix 11", MovieCategory.NEW_RELEASE))
            ids << movieStore.add(command("Spider Man", MovieCategory.REGULAR))
            ids << movieStore.add(command("Spider Man 2", MovieCategory.OLD))

        when:
            def result = movieStore.getAll(ids)

        then:
            result.size() == 3
            result.title == ["Matrix 11", "Spider Man", "Spider Man 2"]
            result.category == [MovieCategory.NEW_RELEASE, MovieCategory.REGULAR, MovieCategory.OLD]
            result.id == ids as List
    }

    NewMovieCommand command(String title, MovieCategory category) {
        NewMovieCommand.builder()
                .title(title)
                .category(category)
                .build()
    }
}
