package com.example.event_management.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

import java.util.stream.Stream;

@Getter
@ToString
public enum EventStatus{
    BOOKED("BOOKED"),
    BOOKMARKED("BOOKMARKED"),
    ATTENDED("ATTENDED");

    private String value;

    EventStatus(String value) {
        this.value=value;
    }

    @JsonCreator
    public static EventStatus decode(final String status){
        return Stream.of(EventStatus.values()).filter(result->result.value.equals(status)).findFirst().orElse(null);
    }

    @JsonValue
    public String getValue(){
        return value;
    }
}
