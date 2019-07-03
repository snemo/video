package com.videorental.common.invoice;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author oleciwoj
 */
public class InvoiceId {
    private final String id;

    private InvoiceId(String id) {
        this.id = id;
    }

    @JsonValue
    public String id() {
        return id;
    }

    public static InvoiceId generate() {
        return new InvoiceId(UUID.randomUUID().toString());
    }

    @JsonCreator
    public static InvoiceId of(String id) {
        checkNotNull(id, "Invoice ID cannot be null");
        checkArgument(id.length() == 36, "Invoice ID has invalid size %s", id);
        return new InvoiceId(id);
    }
}
