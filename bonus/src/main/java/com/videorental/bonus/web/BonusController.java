package com.videorental.bonus.web;

import com.videorental.bonus.BonusAllocator;
import com.videorental.bonus.dto.TotalPointsDto;
import com.videorental.common.customer.CustomerId;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author oleciwoj
 */
@RestController
@RequestMapping("/api/bonus")
@AllArgsConstructor
class BonusController {

    private final BonusAllocator bonusAllocator;

    @GetMapping("/points/{customerId}")
    TotalPointsDto totalPoints(@PathVariable String customerId) {
        return bonusAllocator.customerPoints(CustomerId.of(customerId));
    }
}
