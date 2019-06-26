package com.videorental.common.movie;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author oleciwoj
 */
@ToString
@EqualsAndHashCode
public class MovieId {
    private final String id;

    private MovieId(String id) {
        this.id = id;
    }

    @JsonValue
    public String id() {
        return id;
    }

    public static MovieId generate() {
        return new MovieId(UUID.randomUUID().toString());
    }

    @JsonCreator
    public static MovieId of(String id) {
        checkNotNull(id, "Movie ID cannot be null");
        checkArgument(id.length() == 36, "Movie ID has invalid size %s", id);
        return new MovieId(id);
    }
}
