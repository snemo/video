package com.videorental.mocks.pricing;

import com.videorental.common.pricing.Money;
import com.videorental.pricing.Pricing;
import com.videorental.pricing.command.CalculatePriceCommand;

/**
 * @author oleciwoj
 */
public class PricingMock implements Pricing {

    @Override
    public Money rent(CalculatePriceCommand command) {
        int days = command.getDays();

        switch (command.getMovieCategory()) {
            case NEW_RELEASE:
                return Money.of(days * 40);
            case REGULAR:
                if ( days <= 3 ) {
                    return Money.of(30);
                } else {
                    return Money.of(30 + (30 * (days-3)));
                }
            case OLD:
                if ( days <= 5 ) {
                    return Money.of(30);
                } else {
                    return Money.of(30 + (30 * (days-5)));
                }
            default:
                throw new IllegalStateException("Not supported");
        }
    }

    @Override
    public Money surcharge(CalculatePriceCommand command) {
        int days = command.getDays();

        switch (command.getMovieCategory()) {
            case NEW_RELEASE:
                return Money.of(days * 40);
            case REGULAR:
                return Money.of(days * 30);
            case OLD:
                return Money.of(days * 30);
            default:
                throw new IllegalStateException("Not supported");
        }
    }
}
