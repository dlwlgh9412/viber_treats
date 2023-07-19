package com.sharetreats01.viber_chatbot.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum Type {
    TEXT("text"),
    PICTURE("picture"),
    VIDEO("video"),
    FILE("file"),
    STICKER("sticker"),
    CONTACT("contact"),
    URL("url"),
    LOCATION("location");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    private static final Map<String, Type> map = new HashMap<>();

    static {
        for (Type type : values()) {
            map.put(type.value, type);
        }
    }

    @JsonCreator
    public static Type fromValue(String value) {
        return map.get(value);
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
