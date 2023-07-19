package com.sharetreats01.viber_chatbot.viber.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sharetreats01.viber_chatbot.message.entity.ViberKeyboardEntity;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageRequest {
    private String receiver;
    private Sender sender;
    @JsonProperty("tracking_data")
    private String trackingData;
    @JsonProperty("min_api_version")
    private Integer minApiVersion;
    @JsonProperty("keyboard")
    private ViberKeyboardEntity keyboard;

    public MessageRequest(String receiver, String senderName, String senderAvatar, Integer minApiVersion, ViberKeyboardEntity keyboard, String trackingData) {
        this.receiver = receiver;
        this.sender = new Sender(senderName, senderAvatar);
        this.minApiVersion = minApiVersion;
        this.keyboard = keyboard;
        this.trackingData = trackingData;
    }

    public MessageRequest(String receiver, String senderName, String senderAvatar, String trackingData, Integer minApiVersion) {
        this.receiver = receiver;
        this.sender = new Sender(senderName, senderAvatar);
        this.trackingData = trackingData;
        this.minApiVersion = minApiVersion;
    }

    @Getter
    @RequiredArgsConstructor
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class Sender {
        private String name;
        private String avatar;
    }
}
