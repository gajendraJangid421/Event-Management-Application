package com.example.event_management.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.ToString;

import java.util.stream.Stream;

@Getter
@ToString
public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    Role(String role){
        this.role = role;
    }

    @JsonCreator
    public static Role decode (final String status){
        return Stream.of(Role.values()).filter(result->result.role.equals(status)).findFirst().orElse(null);
    }

    @JsonValue
    public String getRole(){
        return role;
    }

}
