package com.videorental.common.rental;

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
public class OrderId {
    private final String id;

    private OrderId(String id) {
        this.id = id;
    }

    @JsonValue
    public String id() {
        return id;
    }

    public static OrderId generate() {
        return new OrderId(UUID.randomUUID().toString());
    }

    @JsonCreator
    public static OrderId of(String id) {
        checkNotNull(id, "Book ID cannot be null");
        checkArgument(id.length() == 36, "Book ID has invalid size %s", id);
        return new OrderId(id);
    }
}
