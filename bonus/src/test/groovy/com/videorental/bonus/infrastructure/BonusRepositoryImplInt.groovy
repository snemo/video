package com.videorental.bonus.infrastructure

import com.videorental.bonus.command.AddPointsCommand
import com.videorental.bonus.domain.BonusRepository
import com.videorental.common.customer.CustomerId
import com.videorental.common.movie.MovieId
import com.videorental.common.rental.OrderId
import com.videorental.db.config.LiquibaseDataSourceHolder
import spock.lang.Shared
import spock.lang.Specification

/**
 *
 * @author oleciwoj
 */
class BonusRepositoryImplInt extends Specification {

    @Shared BonusRepository repository

    def setupSpec() {
        repository = new BonusRepositoryImpl(LiquibaseDataSourceHolder.getDSLContext())
    }

    def "save points in DB"() {
        given:
            def customerId = CustomerId.generate()
            def command = dummyRepoCommand(customerId, 2)

        when:
            repository.save(command)

        then:
            noExceptionThrown()
    }

    def "get total number of customer bonus points"() {
        given:
            def customerId = CustomerId.generate()
            repository.save(dummyRepoCommand(customerId, 2))
            repository.save(dummyRepoCommand(customerId, 1))
            repository.save(dummyRepoCommand(customerId, 2))

        when:
            def totalPoints = repository.getTotalPointsByCustomerId(customerId)

        then:
            totalPoints != null
            totalPoints.customerId == customerId
            totalPoints.points == BigDecimal.valueOf(5)
    }

    AddPointsCommand dummyRepoCommand(CustomerId customerId, int points) {
        AddPointsCommand.builder()
                .points(points)
                .customerId(customerId)
                .orderId(OrderId.generate())
                .movieId(MovieId.generate())
                .build()
    }
}
