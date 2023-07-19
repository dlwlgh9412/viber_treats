package com.sharetreats01.viber_chatbot.common.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:properties/viber/chatbot.properties")
public class ChatbotProperties {
    @Value("${bot-name}")
    private String botName;

    @Value("${bot-avatar}")
    private String botAvatar;

    @Value("${bot-thumbnail}")
    private String botThumbnail;

    @Value("${bot-min.api.version}")
    private Integer minApiVersion;
}
