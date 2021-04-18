package com.mastery.java.task.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("male"),
    FEMALE("female");


    private final String value;

    private Gender(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Gender getGender(String value) {
        for (Gender e : values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }
}
