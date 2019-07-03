package com.videorental.bonus.domain;

import com.videorental.bonus.command.AddPointsCommand;
import com.videorental.bonus.dto.TotalPointsDto;
import com.videorental.common.customer.CustomerId;

/**
 * @author oleciwoj
 */
public interface BonusRepository {

    void save(AddPointsCommand command);

    TotalPointsDto getTotalPointsByCustomerId(CustomerId customerId);
}
