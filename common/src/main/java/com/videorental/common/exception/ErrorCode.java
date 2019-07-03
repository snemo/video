package com.videorental.common.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.stream.Stream;

/**
 * @author oleciwoj
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    CUSTOMER(100, "Issue in customer component"),
    BONUS(200, "Issue in bonus component"),
    INVOICE(300, "Issue in invoice component"),
    MOVIE(400, "Issue in movie store"),
    PRICING(500, "Issue in pricing component"),
    RENTAL(600, "Issue in rental component"),
    DB(700, "DB issue");

    private final long code;
    private final String description;

    ErrorCode(long code, String description) {
        this.code = code;
        this.description = description;
    }

    @JsonGetter("code")
    public long getCode() {
        return code;
    }

    @JsonGetter("description")
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static ErrorCode of(@JsonProperty("code") long code, @JsonProperty("description") String description) {
        return Stream.of(values())
                .filter(errorCode -> errorCode.code == code)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Could not recognize ErrorCode: " + code + ", " + description));
    }

}