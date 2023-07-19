package com.sharetreats01.viber_chatbot.callback.dto.message.template;

import com.sharetreats01.viber_chatbot.viber.enums.MessageType;
import lombok.Getter;

@Getter
public class WelcomeMessageTemplateValueDto extends MessageTemplateValueDto {
    private final String userName;
    private final String botName;

    public WelcomeMessageTemplateValueDto(MessageType type, String language, String userName, String botName) {
        super(type, language);
        this.userName = userName;
        this.botName = botName;
    }
}
