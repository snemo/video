package com.videorental.common.pricing;

import lombok.Value;

import java.math.BigDecimal;

/**
 * Class can be easily extended with more sophisticated currency support in future.
 *
 * @author oleciwoj
 */
@Value
public class Money {

    private final BigDecimal amount;
    private final Currency currency = Currency.SEK;

    enum Currency {
        SEK
    }
}
