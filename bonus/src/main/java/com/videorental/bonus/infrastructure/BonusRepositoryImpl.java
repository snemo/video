package com.videorental.bonus.infrastructure;

import com.videorental.bonus.command.AddPointsCommand;
import com.videorental.bonus.domain.BonusRepository;
import com.videorental.bonus.dto.TotalPointsDto;
import com.videorental.bonus.exception.BonusException;
import com.videorental.common.customer.CustomerId;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static com.videorental.db.generated.tables.Bonuses.BONUSES;
import static org.jooq.impl.DSL.sum;

/**
 * @author oleciwoj
 */
@Repository
@AllArgsConstructor
class BonusRepositoryImpl implements BonusRepository {

    private final DSLContext dsl;

    @Override
    public void save(AddPointsCommand command) {
        var rows = dsl.insertInto(BONUSES)
                .set(BONUSES.CUSTOMER_ID, command.getCustomerId().id())
                .set(BONUSES.POINTS, command.getPoints())
                .set(BONUSES.ODER_ID, command.getOrderId().id())
                .set(BONUSES.MOVIE_ID, command.getMovieId().id())
                .execute();
        if (rows != 1)
            throw new BonusException("Could not write Bonus into DB: " + command);
    }

    @Override
    public TotalPointsDto getTotalPointsByCustomerId(CustomerId customerId) {
        return dsl.select(sum(BONUSES.POINTS))
                .from(BONUSES)
                .where(BONUSES.CUSTOMER_ID.eq(customerId.id()))
                .fetchOptional()
                .map(p -> TotalPointsDto.of(customerId, p.value1()))
                .orElseGet(() -> TotalPointsDto.zero(customerId));
    }
}
