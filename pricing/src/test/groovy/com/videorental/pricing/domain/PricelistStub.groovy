package com.videorental.pricing.domain

import com.videorental.common.pricing.Money

/**
 *
 * @author oleciwoj
 */
class PricelistStub implements Pricelist {
    @Override
    Money getPrice(PriceCategory category) {
        switch (category) {
            case PriceCategory.BASIC:
                return Money.of(30)
            case PriceCategory.PREMIUM:
                return Money.of(40)
        }
    }
}
