package com.videorental.pricing.domain;

import com.videorental.common.movie.MovieCategory;

import java.util.Map;
import java.util.Optional;

/**
 * @author oleciwoj
 */
class PriceStrategyManager {

    private static final Map<MovieCategory, PriceStrategy> strategies;

    static {
        strategies = Map.of(
                MovieCategory.NEW_RELEASE, new NewReleaseStrategy(),
                MovieCategory.REGULAR, new RegularStrategy(),
                MovieCategory.OLD, new OldStrategy());
    }

    static PriceStrategy getStrategy(MovieCategory category) {
        return Optional.ofNullable(strategies.get(category))
                .orElseThrow(() -> new IllegalStateException("Movie category not supported: " + category));
    }
}
