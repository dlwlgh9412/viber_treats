package com.sharetreats01.viber_chatbot.callback.dto.message.template;

import com.sharetreats01.viber_chatbot.viber.enums.MessageType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class MessageTemplateValueDto {
    private final MessageType type;
    private final String language;
}
