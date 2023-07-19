package com.sharetreats01.viber_chatbot.callback.service;

import com.sharetreats01.viber_chatbot.callback.dto.callback.request.CallbackDtoConversationStarted;
import com.sharetreats01.viber_chatbot.callback.dto.callback.request.property.User;
import com.sharetreats01.viber_chatbot.callback.dto.callback.response.ConversationStartedDtoResponse;
import com.sharetreats01.viber_chatbot.callback.dto.message.template.WelcomeMessageTemplateValueDto;
import com.sharetreats01.viber_chatbot.viber.enums.MessageType;
import com.sharetreats01.viber_chatbot.common.properties.ChatbotProperties;
import com.sharetreats01.viber_chatbot.common.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class CallbackServiceConversationStarted implements CallbackService<CallbackDtoConversationStarted, ConversationStartedDtoResponse> {
    private final ChatbotProperties properties;
    private final MessageService messageService;

    @Override
    public Class<CallbackDtoConversationStarted> getCallbackType() {
        return CallbackDtoConversationStarted.class;
    }

    @Override
    public ConversationStartedDtoResponse handleEvent(CallbackDtoConversationStarted request) {
        User user = request.getUser();
        String message = messageService.createMessage(
                new WelcomeMessageTemplateValueDto(MessageType.WELCOME, user.getLanguage(), user.getName(), properties.getBotName()));
        return ConversationStartedDtoResponse.builder()
                .senderName(properties.getBotName())
                .senderAvatar(properties.getBotAvatar())
                .type("text")
                .text(message)
                .build();
    }
}
