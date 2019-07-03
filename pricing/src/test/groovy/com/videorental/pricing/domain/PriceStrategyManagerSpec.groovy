package com.videorental.pricing.domain

import com.videorental.common.pricing.Money
import spock.lang.Specification

import static com.videorental.common.movie.MovieCategory.*

/**
 *
 * @author oleciwoj
 */
class PriceStrategyManagerSpec extends Specification {

    def "get strategy for new release movies"() {
        given:
            def strategy = PriceStrategyManager.getStrategy(NEW_RELEASE)

        when:
            def rentPrice = strategy.rent(new PricelistStub(), days)
            def surchargePrice = strategy.surcharge(new PricelistStub(), days)

        then:
            rentPrice == expectedRentPrice
            surchargePrice == expectedsurchargePrice

        where:
        days        || expectedRentPrice            || expectedsurchargePrice
        1           || Money.of(40)         || Money.of(40)
        3           || Money.of(120)        || Money.of(120)
        10          || Money.of(400)        || Money.of(400)
    }

    def "get strategy for regular movies"() {
        given:
            def strategy = PriceStrategyManager.getStrategy(REGULAR)

        when:
            def rentPrice = strategy.rent(new PricelistStub(), days)
            def surchargePrice = strategy.surcharge(new PricelistStub(), days)

        then:
            rentPrice == expectedRentPrice
            surchargePrice == expectedsurchargePrice

        where:
        days        || expectedRentPrice            || expectedsurchargePrice
        1           || Money.of(30)         || Money.of(30)
        2           || Money.of(30)         || Money.of(60)
        3           || Money.of(30)         || Money.of(90)
        5           || Money.of(90)         || Money.of(150)
        10          || Money.of(240)        || Money.of(300)
    }

    def "get strategy for old movies"() {
        given:
            def strategy = PriceStrategyManager.getStrategy(OLD)

        when:
            def rentPrice = strategy.rent(new PricelistStub(), days)
            def surchargePrice = strategy.surcharge(new PricelistStub(), days)

        then:
            rentPrice == expectedRentPrice
            surchargePrice == expectedsurchargePrice

        where:
        days        || expectedRentPrice            || expectedsurchargePrice
        1           || Money.of(30)         || Money.of(30)
        3           || Money.of(30)         || Money.of(90)
        5           || Money.of(30)         || Money.of(150)
        10          || Money.of(180)        || Money.of(300)
    }
}
