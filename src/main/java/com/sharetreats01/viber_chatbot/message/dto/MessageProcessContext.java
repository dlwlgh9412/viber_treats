package com.sharetreats01.viber_chatbot.message.dto;

import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import com.sharetreats01.viber_chatbot.callback.enums.TreatState;
import lombok.Data;

@Data
public class MessageProcessContext {
    private String receiverId;
    private String senderName;
    private String senderAvatar;
    private Integer minApiVersion;
    private String input;
    private String trackingData;
    private MessageState messageState;
    private TreatState treatState;

    public MessageProcessContext(String receiverId, String senderName, String senderAvatar, Integer minApiVersion, String input, String trackingData, MessageState messageState, TreatState treatState) {
        this.receiverId = receiverId;
        this.senderName = senderName;
        this.senderAvatar = senderAvatar;
        this.minApiVersion = minApiVersion;
        this.input = input;
        this.trackingData = trackingData;
        this.messageState = messageState;
        this.treatState = treatState;
    }

    public MessageProcessContext(String receiverId, String senderName, String senderAvatar, Integer minApiVersion, String input, String trackingData) {
        this.receiverId = receiverId;
        this.senderName = senderName;
        this.senderAvatar = senderAvatar;
        this.minApiVersion = minApiVersion;
        this.input = input;
        this.trackingData = trackingData;
    }
}
