package com.videorental.bonus.domain;

import com.videorental.bonus.BonusAllocator;
import com.videorental.bonus.command.AddPointsCommand;
import com.videorental.bonus.dto.TotalPointsDto;
import com.videorental.common.customer.CustomerId;
import com.videorental.common.movie.MovieCategory;
import com.videorental.common.movie.MovieId;
import com.videorental.common.rental.MovieRentedEvent;
import com.videorental.movie.MovieStore;
import com.videorental.movie.dto.MovieDetailsDto;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author oleciwoj
 */
@Service
@AllArgsConstructor
class BonusAllocatorImpl implements BonusAllocator {

    private final BonusRepository repository;
    private final MovieStore movieStore;

    @Override
    @Transactional
    public TotalPointsDto customerPoints(CustomerId customerId) {
        return repository.getTotalPointsByCustomerId(customerId);
    }

    @Override
    @Transactional
    public void addPoints(AddPointsCommand command) {
        repository.save(command);
    }

    @EventListener
    @Transactional
    public void on(MovieRentedEvent event) {
        var movieCategories = getMovieCategories(event.getMovies().stream()
                .map(MovieRentedEvent.MovieItem::getMovieId).collect(Collectors.toSet()));

        for (var movie : event.getMovies()) {
            addPoints(AddPointsCommand.builder()
                    .customerId(event.getCustomer())
                    .orderId(event.getOrder())
                    .points(BonusPolicy.points(movieCategories.get(movie.getMovieId())))
                    .movieId(movie.getMovieId())
                    .build());
        }
    }

    private Map<MovieId, MovieCategory> getMovieCategories(Set<MovieId> ids) {
        return movieStore.getAll(ids).stream()
                .collect(Collectors.toMap(MovieDetailsDto::getId, MovieDetailsDto::getCategory));
    }
}
