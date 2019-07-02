package com.videorental.pricing.domain;

import com.videorental.common.movie.MovieCategory;
import com.videorental.common.pricing.Money;
import com.videorental.pricing.Pricing;
import com.videorental.pricing.command.CalculatePriceCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author oleciwoj
 */
@Service
@AllArgsConstructor
class PricingCalculator implements Pricing {

    private final Pricelist pricelist;

    @Override
    public Money rent(CalculatePriceCommand cmd) {
        return PriceStrategyManager.getStrategy(cmd.getMovieCategory())
                .rent(pricelist, cmd.getDays());
    }

    @Override
    public Money surcharge(CalculatePriceCommand cmd) {
            return PriceStrategyManager.getStrategy(cmd.getMovieCategory())
                    .surcharge(pricelist, cmd.getDays());
    }
}
