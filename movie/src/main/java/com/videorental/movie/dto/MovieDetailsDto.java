package com.videorental.movie.dto;

import com.videorental.common.movie.MovieCategory;
import com.videorental.common.movie.MovieId;
import com.videorental.db.generated.tables.records.MoviesRecord;
import lombok.Builder;
import lombok.Value;

/**
 * @author oleciwoj
 */
@Value
@Builder
public class MovieDetailsDto {
    private final MovieId id;
    private final String title;
    private final MovieCategory category;

    public static MovieDetailsDto of(MoviesRecord record) {
        return builder()
                .id(MovieId.of(record.getId()))
                .title(record.getTitle())
                .category(MovieCategory.of(record.getCategory()))
                .build();
    }
}
