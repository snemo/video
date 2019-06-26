package com.videorental.pricing;

import com.videorental.common.pricing.Money;
import com.videorental.pricing.command.CalculatePriceCommand;

/**
 * @author oleciwoj
 */
public interface Pricing {

    Money renting(CalculatePriceCommand cmd);

    Money surcharges(CalculatePriceCommand cmd);
}
