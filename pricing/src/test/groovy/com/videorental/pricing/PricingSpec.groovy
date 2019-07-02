package com.videorental.pricing

import com.videorental.common.movie.MovieCategory
import com.videorental.common.pricing.Money
import com.videorental.pricing.command.CalculatePriceCommand
import com.videorental.pricing.domain.PricingCalculator
import com.videorental.pricing.infrastructure.PricelistInMemory
import spock.lang.Shared
import spock.lang.Specification

import static com.videorental.common.movie.MovieCategory.*

/**
 *
 * @author oleciwoj
 */
class PricingSpec extends Specification {

    @Shared Pricing pricing

    def setupSpec() {
        pricing = new PricingCalculator(new PricelistInMemory())
    }

    def "calculate price for renting a movie"() {
        given:
            def command = new CalculatePriceCommand(days, movieCategory)

        when:
            def price = pricing.rent(command)

        then:
            price == expectedPrice

        where:
        days    || movieCategory         || expectedPrice
        1       || NEW_RELEASE           || Money.of(40)
        10      || NEW_RELEASE           || Money.of(400)
        1       || REGULAR               || Money.of(30)
        3       || REGULAR               || Money.of(30)
        5       || REGULAR               || Money.of(90)
        1       || OLD                   || Money.of(30)
        3       || OLD                   || Money.of(30)
        5       || OLD                   || Money.of(30)
        7       || OLD                   || Money.of(90)
    }

    def "calculate surcharge price"() {
        given:
        def command = new CalculatePriceCommand(days, movieCategory)

        when:
        def price = pricing.surcharge(command)

        then:
        price == expectedPrice

        where:
        days    || movieCategory         || expectedPrice
        1       || NEW_RELEASE           || Money.of(40)
        3       || NEW_RELEASE           || Money.of(120)
        1       || REGULAR               || Money.of(30)
        3       || REGULAR               || Money.of(90)
        1       || OLD                   || Money.of(30)
        3       || OLD                   || Money.of(90)
    }
}
