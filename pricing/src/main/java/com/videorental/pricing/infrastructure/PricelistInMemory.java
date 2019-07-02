package com.videorental.pricing.infrastructure;

import com.videorental.common.pricing.Money;
import com.videorental.pricing.domain.Pricelist;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * In memory implementation of pricelist.
 * It can be easily replaced with different implementation (e.g. DB)
 *
 * @author oleciwoj
 */
@Component
class PricelistInMemory implements Pricelist {

    @Override
    public Money getPrice(PriceCategory category) {
        switch (category) {
            case BASIC:
                return Money.of(BigDecimal.valueOf(30));
            case PREMIUM:
                return Money.of(BigDecimal.valueOf(40));
             default:
                 throw new IllegalStateException("Not supported price category");
        }
    }
}
