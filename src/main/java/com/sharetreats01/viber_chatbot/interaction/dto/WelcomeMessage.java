package com.sharetreats01.viber_chatbot.interaction.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class WelcomeMessage {
    private final Sender sender;
    private final String trackingData;
    private final String type;
    private final String text;
    private final String media;
    private final String thumbnail;

    @Builder
    public WelcomeMessage(String senderName, String senderAvatar, String trackingData, String type, String text, String media, String thumbnail) {
        this.sender = new Sender(senderName, senderAvatar);
        this.trackingData = trackingData;
        this.type = type;
        this.text = text;
        this.media = media;
        this.thumbnail = thumbnail;
    }

    @Getter
    @RequiredArgsConstructor
    private static class Sender {
        private final String name;
        private final String avatar;
    }
}