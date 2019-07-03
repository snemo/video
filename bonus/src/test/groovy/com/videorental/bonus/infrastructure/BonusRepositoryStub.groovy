package com.videorental.bonus.infrastructure

import com.videorental.bonus.command.AddPointsCommand
import com.videorental.bonus.domain.BonusRepository
import com.videorental.bonus.dto.TotalPointsDto
import com.videorental.common.customer.CustomerId

import java.util.stream.Collectors

/**
 *
 * @author oleciwoj
 */
class BonusRepositoryStub implements BonusRepository {

    List<AddPointsCommand> store = []

    @Override
    void save(AddPointsCommand command) {
        store << command
    }

    @Override
    TotalPointsDto getTotalPointsByCustomerId(CustomerId customerId) {
        def totalPrice = store
                .findAll {it.customerId == customerId}
                .points
                .sum()

        return new TotalPointsDto(customerId, BigDecimal.valueOf(totalPrice))
    }
}
