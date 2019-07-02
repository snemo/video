package com.videorental.pricing.domain;

import com.videorental.common.pricing.Money;

/**
 * @author oleciwoj
 */

interface PriceStrategy {

    Money rent(Pricelist pricelist, int days);

    Money surcharge(Pricelist pricelist, int days);
}

class NewReleaseStrategy implements PriceStrategy {
    @Override
    public Money rent(Pricelist pricelist, int days) {
        return pricelist.getPrice(Pricelist.PriceCategory.PREMIUM)
            .multiplyAmount(days);
}

    @Override
    public Money surcharge(Pricelist pricelist, int days) {
        return pricelist.getPrice(Pricelist.PriceCategory.PREMIUM)
                .multiplyAmount(days);
    }
}

class RegularStrategy implements PriceStrategy {
    @Override
    public Money rent(Pricelist pricelist, int days) {
        var basicPrice = pricelist.getPrice(Pricelist.PriceCategory.BASIC);

        if ( days <= 3 ) {
            return basicPrice;
        } else {
            return basicPrice.add(basicPrice.multiplyAmount(days - 3));
        }
    }

    @Override
    public Money surcharge(Pricelist pricelist, int days) {
        return pricelist.getPrice(Pricelist.PriceCategory.BASIC)
                .multiplyAmount(days);
    }
}

class OldStrategy implements PriceStrategy {
    @Override
    public Money rent(Pricelist pricelist, int days) {
        var basicPrice = pricelist.getPrice(Pricelist.PriceCategory.BASIC);

        if ( days <= 5 ) {
            return basicPrice;
        } else {
            return basicPrice.add(basicPrice.multiplyAmount(days - 5));
        }
    }

    @Override
    public Money surcharge(Pricelist pricelist, int days) {
        return pricelist.getPrice(Pricelist.PriceCategory.BASIC)
                .multiplyAmount(days);
    }
}