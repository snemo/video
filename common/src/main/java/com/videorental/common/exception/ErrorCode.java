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

    API(100, "Issue in API component"),
    WEB(200, "Issue in WEB component"),
    DB(300, "DB issue");

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