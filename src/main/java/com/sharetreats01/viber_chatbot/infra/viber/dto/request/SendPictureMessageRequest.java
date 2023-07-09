package com.sharetreats01.viber_chatbot.infra.viber.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class SendPictureMessageRequest extends SendMessageRequest {
    private final String type = "picture";
    private final String media;
    private final String thumbnail;
    private String text;

    public SendPictureMessageRequest(String receiver, String senderName, String senderAvatar, Integer minApiVersion, String trackingData, String media, String thumbnail, String text) {
        super(receiver, new Sender(senderName, senderAvatar), minApiVersion, trackingData);
        this.media = media;
        this.thumbnail = thumbnail;
        this.text = text;
    }
}
