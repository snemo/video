package com.videorental.movie.command;

import com.videorental.common.movie.MovieCategory;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * @author oleciwoj
 */
@Value
@Builder
public class NewMovieCommand {
    @NonNull private final String title;
    @NonNull private final MovieCategory category;
}
