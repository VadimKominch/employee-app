package com.mastery.java.task.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Gender implements Serializable {
    MALE("MALE"),
    FEMALE("FEMALE");


    private final String value;

    Gender(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Gender getGender(String value) {
        for (Gender e : values()) {
            if (e.value.toUpperCase().equals(value.toUpperCase())) {
                return e;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return value;
    }
}
