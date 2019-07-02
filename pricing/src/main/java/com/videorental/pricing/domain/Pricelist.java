package com.videorental.pricing.domain;

import com.videorental.common.pricing.Money;

/**
 * @author oleciwoj
 */
public interface Pricelist {

    Money getPrice(PriceCategory category);

    enum PriceCategory {
        PREMIUM, BASIC
    }
}
