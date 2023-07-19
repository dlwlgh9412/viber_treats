package com.sharetreats01.viber_chatbot.callback.utils;

import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;


@Component
@RequiredArgsConstructor
public class MessageUtils {
    private final Map<MessageState, MessageState> handlerPath;
    private final UUIDGenerator uuidGenerator;
    private final String DELIMITER = ":";
    private final String INPUT_DELIMITER = "_";
    private final String TREAT_STATE = MessageState.TREAT.name();
    private final String NEW = MessageState.NEW.name();
    private final String PRODUCTS = MessageState.PRODUCTS.name();

    public String processSendTreatTrackingData(String trackingData, String input) {
        if (input.contains(INPUT_DELIMITER)) {
            String[] parts = input.split(INPUT_DELIMITER);
            return trackingData + INPUT_DELIMITER + parts[0];
        }
        return trackingData;
    }

    public String processSendTreatInput(String input) {
        if (input.contains(INPUT_DELIMITER)) {
            String[] parts = input.split(INPUT_DELIMITER);
            return parts[1];
        }
        return input;
    }

    public String createSession() {
        return uuidGenerator.createTimeBasedUUID().toString();
    }

    public String createTrackingData() {
        return createSession() + DELIMITER + MessageState.BRANDS.name();
    }

    public MessageState extractHandleKey(String trackingData, String input) {
        if (!StringUtils.hasText(trackingData) || input.equals(NEW)) return MessageState.NEW;
        if (isTreatProcess(trackingData, input)) return MessageState.TREAT;

        String[] parts = trackingData.split(DELIMITER);

        if (parts.length == 2 && !StringUtils.hasText(input)) {
            return MessageState.BRANDS;
        } else if (parts.length == 2 && StringUtils.hasText(input)) {
            return MessageState.PRODUCTS;
        } else if (parts.length == 3 && StringUtils.hasText(input)) {
            return MessageState.DETAIL;
        } else if (parts.length == 4) {
            return MessageState.TREAT;
        } else {
            return MessageState.NEW;
        }
    }

    public MessageState extractMessageKey(String trackingData, String input) {
        if (input != null && input.contains(TREAT_STATE)) return null;

        if (!StringUtils.hasText(trackingData) || StringUtils.hasText(input) && input.equals(NEW)) return MessageState.BRANDS;
        return extractHandleKey(trackingData, input);
    }

    public boolean isTreatProcess(String trackingData, String input) {
        if (input != null && input.contains(TREAT_STATE)) return true;
        if (trackingData == null) return false;
        String[] parts = trackingData.split(DELIMITER);
        return parts[parts.length - 1].startsWith(TREAT_STATE);
    }


    public String createReplyTrackingData(String trackingData, String state, String input) {
        return trackingData + INPUT_DELIMITER + input + DELIMITER + state;
    }
}
