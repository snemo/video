package com.videorental.bonus

import com.videorental.bonus.command.AddPointsCommand
import com.videorental.bonus.domain.BonusAllocatorImpl
import com.videorental.bonus.infrastructure.BonusRepositoryStub
import com.videorental.common.customer.CustomerId
import com.videorental.common.movie.MovieId
import com.videorental.common.rental.OrderId
import com.videorental.mocks.movie.MovieStoreMock
import spock.lang.Shared
import spock.lang.Specification

/**
 *
 * @author oleciwoj
 */
class BonusAllocatorSpec extends Specification {

    @Shared MovieStoreMock movieStore
    @Shared BonusAllocator bonusAllocator

    def setupSpec() {
        movieStore = new MovieStoreMock()
        bonusAllocator = new BonusAllocatorImpl(new BonusRepositoryStub(), movieStore)
    }

    def "add bonus points to customer"() {
        given:
            def customerId = CustomerId.generate()
            def command = dummyCommand(customerId, 2)

        when:
            bonusAllocator.addPoints(command)

        then:
            noExceptionThrown()
    }

    def "get total bonus points of customer"() {
        given:
            def customerId = CustomerId.generate()
            bonusAllocator.addPoints(dummyCommand(customerId, 2))
            bonusAllocator.addPoints(dummyCommand(customerId, 1))
            bonusAllocator.addPoints(dummyCommand(customerId, 2))

        when:
            def totalPoints = bonusAllocator.customerPoints(customerId)

        then:
            totalPoints != null
            totalPoints.customerId == customerId
            totalPoints.points == BigDecimal.valueOf(5)
    }

    AddPointsCommand dummyCommand(CustomerId customerId, int points) {
        AddPointsCommand.builder()
                .points(points)
                .customerId(customerId)
                .orderId(OrderId.generate())
                .movieId(MovieId.generate())
                .build()
    }
}
