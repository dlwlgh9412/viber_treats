package com.sharetreats01.viber_chatbot.viber.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public final class SetWebhookRequest {
    private final String url;
    @JsonProperty("event_types")
    private final List<String> eventTypes;
    @JsonProperty("send_name")
    private final Boolean sendName;
    @JsonProperty("send_photo")
    private final Boolean sendPhoto;
}
