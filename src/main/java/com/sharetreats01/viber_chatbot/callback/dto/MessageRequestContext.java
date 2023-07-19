package com.sharetreats01.viber_chatbot.callback.dto;

import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import com.sharetreats01.viber_chatbot.callback.enums.TreatState;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageRequestContext {
    private String receiverId;
    private String senderName;
    private String senderAvatar;
    private Integer minApiVersion;
    private String input;
    private String trackingData;
    private MessageState messageHandlePathKey;
    private TreatState treatHandlePathKey;

    private MessageRequestContext(String receiverId, String senderName, String senderAvatar, Integer minApiVersion, String input, String trackingData) {
        this.receiverId = receiverId;
        this.senderName = senderName;
        this.senderAvatar = senderAvatar;
        this.minApiVersion = minApiVersion;
        this.input = input;
        this.trackingData = trackingData;
    }

    public static MessageRequestContext create(String receiverId, String senderName, String senderAvatar, Integer minApiVersion, String trackingData, String input) {
        return new MessageRequestContext(receiverId, senderName, senderAvatar, minApiVersion, input, trackingData);
    }
}
