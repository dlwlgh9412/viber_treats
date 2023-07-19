package com.sharetreats01.viber_chatbot.viber.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sharetreats01.viber_chatbot.message.entity.ViberKeyboardEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TextMessageRequest extends MessageRequest {
    private final String type = "text";
    private final String text;

    private TextMessageRequest(String receiver, String senderName, String senderAvatar, Integer minApiVersion, ViberKeyboardEntity keyboard, String trackingData, String text) {
        super(receiver, senderName, senderAvatar, minApiVersion, keyboard, trackingData);
        this.text = text;
    }

    private TextMessageRequest(String receiver, String senderName, String senderAvatar, String trackingData, Integer minApiVersion, String text) {
        super(receiver, senderName, senderAvatar, trackingData, minApiVersion);
        this.text = text;
    }

    public static TextMessageRequest createDefault(String receiver, String senderName, String senderAvatar, Integer minApiVersion, String trackingData, String text) {
        return new TextMessageRequest(receiver, senderName, senderAvatar, trackingData, minApiVersion, text);
    }

    public static TextMessageRequest createWithKeyboard(String receiver, String senderName, String senderAvatar, Integer minApiVersion, ViberKeyboardEntity keyboard, String trackingData, String text) {
        return new TextMessageRequest(receiver, senderName, senderAvatar, minApiVersion, keyboard, trackingData, text);
    }
}
