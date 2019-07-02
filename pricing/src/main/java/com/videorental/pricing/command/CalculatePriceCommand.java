package com.videorental.pricing.command;

import com.videorental.common.movie.MovieCategory;
import lombok.Builder;
import lombok.Value;

/**
 * @author oleciwoj
 */
@Value
@Builder
public class CalculatePriceCommand {
    private final int days;
    private final MovieCategory movieCategory;
}

