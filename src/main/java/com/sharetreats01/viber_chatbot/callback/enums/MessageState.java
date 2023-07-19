package com.sharetreats01.viber_chatbot.callback.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

public enum MessageState {
    NEW,
    BRANDS,
    PRODUCTS,
    DETAIL,
    PAYMENT,
    TREAT;
    private static final Map<String, MessageState> map = new HashMap<>();

    static {
        for (MessageState messageState : values()) {
            map.put(messageState.name(), messageState);
        }
    }

    @JsonCreator
    public static MessageState fromValue(String value) {
        return map.getOrDefault(value, NEW);
    }
}
