package com.videorental.bonus;

import com.videorental.bonus.command.AddPointsCommand;
import com.videorental.bonus.dto.TotalPointsDto;
import com.videorental.common.customer.CustomerId;

/**
 * @author oleciwoj
 */
public interface BonusAllocator {

    TotalPointsDto customerPoints(CustomerId customerId);

    void addPoints(AddPointsCommand command);
}
