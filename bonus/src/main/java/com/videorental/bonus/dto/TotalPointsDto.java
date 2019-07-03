package com.videorental.bonus.dto;

import com.videorental.common.customer.CustomerId;
import lombok.Value;

import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author oleciwoj
 */
@Value
public class TotalPointsDto {
    private final CustomerId customerId;
    private final BigDecimal points;

    public static TotalPointsDto of(CustomerId customerId, BigDecimal points) {
        checkNotNull(customerId);
        checkNotNull(points);
        return new TotalPointsDto(customerId, points);
    }

    public static TotalPointsDto zero(CustomerId customerId) {
        return of(customerId, BigDecimal.ZERO);
    }
}
