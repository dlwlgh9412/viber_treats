package com.sharetreats01.viber_chatbot.callback.dto.callback.response;

import com.sharetreats01.viber_chatbot.callback.dto.callback.response.property.Sender;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConversationStartedDtoResponse extends CallbackDtoResponse {
    private final Sender sender;
    private final String type;
    private final String text;
    private final String media;
    private final String thumbnail;

    @Builder
    public ConversationStartedDtoResponse(String senderName, String senderAvatar, String type, String text, String media, String thumbnail) {
        this.sender = new Sender(senderName, senderAvatar);
        this.type = type;
        this.text = text;
        this.media = media;
        this.thumbnail = thumbnail;
    }
}
