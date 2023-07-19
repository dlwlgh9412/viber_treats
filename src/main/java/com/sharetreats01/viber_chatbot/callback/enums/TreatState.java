package com.sharetreats01.viber_chatbot.callback.enums;

import java.util.HashMap;
import java.util.Map;

public enum TreatState {
    FOR,
    ME,
    FRIEND,
    RECIPIENT,
    YOUR_INFO,
    MESSAGE,
    PROMO_CODE,
    PAYMENT,
    TREAT,
    TREAT_COMPLETE;

    public static final Map<String, TreatState> map = new HashMap<>();

    static {
        for (TreatState constant : values()) {
            map.put(constant.name(), constant);
        }
    }

    public static TreatState fromValue(String value) {
        return map.getOrDefault(value, null);
    }
}
