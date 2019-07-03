package com.videorental.common.movie;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

/**
 * @author oleciwoj
 */
public enum MovieCategory {
    NEW_RELEASE("new_release"),
    REGULAR("regular"),
    OLD("old");

    private final String id;

    MovieCategory(String id) {
        this.id = id;
    }

    @JsonValue
    public String id() {
        return id;
    }

    @JsonCreator
    public static MovieCategory of(String id) {
        return Stream.of(values())
                .filter(category -> category.id.equals(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Could not recognize Movie Category: " + id));
    }
}
