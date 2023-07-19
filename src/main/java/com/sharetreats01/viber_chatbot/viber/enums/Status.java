package com.sharetreats01.viber_chatbot.viber.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    OK(0),
    INVALID_URL(1),
    INVALID_AUTH_TOKEN(2),
    BAD_DATA(3),
    MISSING_DATA(4),
    RECEIVER_NOT_REGISTERED(5),
    RECEIVER_NOT_SUBSCRIBED(6),
    PUBLIC_ACCOUNT_BLOCKED(7),
    PUBLIC_ACCOUNT_NOT_FOUND(8),
    PUBLIC_ACCOUNT_SUSPEND(9),
    WEB_HOOK_NOT_SET(10),
    RECEIVER_NO_SUITABLE_DEVICE(11),
    TOO_MANY_REQUEST(12),
    API_VERSION_NOT_SUPPORTED(13),
    INCOMPATIBLE_WITH_VERSION(14),
    PUBLIC_ACCOUNT_NOT_AUTHORIZED(15),
    IN_CHAT_REPLY_MESSAGE_NOT_ALLOWED(16),
    PUBLIC_ACCOUNT_IS_NOT_INLINE(17),
    NO_PUBLIC_CHAT(18),
    CANNOT_SEND_BROAD_CAST(19),
    BROAD_CAST_NOT_ALLOWED(20),
    UNSUPPORTED_COUNTRY(21),
    PAYMENT_UNSUPPORTED(22),
    FREE_MESSAGE_EXCEEDED(23),
    NO_BALANCE(24),
    GENERAL_ERROR(null)
    ;
    private final Integer value;
    private static final Map<Integer, Status> map = new HashMap<>();

    Status(Integer value) {
        this.value = value;
    }

    static {
        for (Status status : values()) {
            map.put(status.value, status);
        }
    }

    @JsonCreator
    public static Status fromValue(Integer value) {
        return map.getOrDefault(value, GENERAL_ERROR);
    }

    @JsonValue
    public Integer getValue() {
        return value;
    }

    public String getMessage() {
        return name();
    }
}

