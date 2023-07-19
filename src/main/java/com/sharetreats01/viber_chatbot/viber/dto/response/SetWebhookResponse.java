package com.sharetreats01.viber_chatbot.viber.dto.response;

import com.sharetreats01.viber_chatbot.viber.enums.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public final class SetWebhookResponse {
    private final Status status;
    private final String statusMessage;
    private final List<String> eventTypes;

    @Override
    public String toString() {
        return "SetWebhookResponse{" +
                "status=" + status +
                ", statusMessage='" + statusMessage + '\'' +
                ", eventTypes=" + eventTypes +
                '}';
    }
}
