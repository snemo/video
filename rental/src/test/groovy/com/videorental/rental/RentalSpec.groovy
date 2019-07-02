package com.videorental.rental

import com.videorental.common.customer.CustomerId
import com.videorental.common.movie.MovieId
import com.videorental.rental.command.RentCommand
import com.videorental.rental.command.ReturnCommand
import com.videorental.rental.domain.RentalService
import com.videorental.rental.infrastructure.RentalRepositoryStub
import org.springframework.context.ApplicationEventPublisher
import spock.lang.Shared
import spock.lang.Specification

/**
 *
 * @author oleciwoj
 */
class RentalSpec extends Specification {

    @Shared Rental rental
    @Shared ApplicationEventPublisher applicationEventPublisher

    def setupSpec() {
        applicationEventPublisher = Mock(ApplicationEventPublisher)
        rental = new RentalService(applicationEventPublisher, new RentalRepositoryStub())
    }

    def "rent movies"() {
        given:
            def customerId = CustomerId.generate()
            def command = dummyRentCommand(customerId)

        when:
            def result = rental.rentMovie(command)

        then:
            result != null
            result.id() != null
//            1 * applicationEventPublisher.publishEvent(_ as MovieRentedEvent)
    }

    def "return movie"() {
        given:
            def customerId = CustomerId.generate()
            def command = dummyReturnCommand(customerId)
        when:
            def result = rental.returnMovie(command)

        then:
            result != null
            result.id() != null
//            1 * applicationEventPublisher.publishEvent(_ as MovieReturnedEvent)
    }

    RentCommand dummyRentCommand(CustomerId customerId) {
        RentCommand.builder()
                .customer(customerId)
                .movies([
                        new RentCommand.Item(MovieId.generate(), 1),
                        new RentCommand.Item(MovieId.generate(), 2),
                        new RentCommand.Item(MovieId.generate(), 3),
                        new RentCommand.Item(MovieId.generate(), 4)
                ])
                .build()
    }

    ReturnCommand dummyReturnCommand(CustomerId customerId) {
        ReturnCommand.builder()
                .customer(customerId)
                .movies([
                        new ReturnCommand.ReturnedMovie(MovieId.generate(), 1),
                        new ReturnCommand.ReturnedMovie(MovieId.generate(), 2),
                        new ReturnCommand.ReturnedMovie(MovieId.generate(), 3),
                        new ReturnCommand.ReturnedMovie(MovieId.generate(), 4)
                ]).build()
    }
}
