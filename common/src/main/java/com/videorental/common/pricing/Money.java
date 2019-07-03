package com.videorental.common.pricing;

import lombok.Value;

import java.math.BigDecimal;

/**
 * Class can be easily extended with more sophisticated currency support in future.
 * Based on FAQ, we are supporting only SEK currency.
 *
 * @author oleciwoj
 */
@Value
public class Money {

    public static final Money ZERO = of(BigDecimal.ZERO);

    private final BigDecimal amount;
    private final Currency currency = Currency.SEK;

    enum Currency {
        SEK
    }

    public static Money of(BigDecimal amount) {
        return new Money(amount);
    }

    public static Money of(int amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public Money multiplyAmount(int x) {
        return Money.of(amount.multiply(BigDecimal.valueOf(x)));
    }

    public Money add(Money money) {
        return Money.of(amount.add(money.getAmount()));
    }
}
