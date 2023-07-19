package com.sharetreats01.viber_chatbot.callback.service;

import com.sharetreats01.viber_chatbot.callback.dto.callback.request.CallbackDtoSubscribed;
import com.sharetreats01.viber_chatbot.callback.dto.callback.response.SubscribeDtoResponse;
import com.sharetreats01.viber_chatbot.common.properties.ChatbotProperties;
import com.sharetreats01.viber_chatbot.message.dto.MessageProcessContext;
import com.sharetreats01.viber_chatbot.message.service.MessageService;
import com.sharetreats01.viber_chatbot.viber.dto.request.MessageRequest;
import com.sharetreats01.viber_chatbot.user.service.UserService;
import com.sharetreats01.viber_chatbot.viber.client.ViberWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CallbackServiceSubScribed implements CallbackService<CallbackDtoSubscribed, SubscribeDtoResponse> {
    private final UserService userService;
    private final ChatbotProperties chatbotProperties;
    private final ViberWebClient viberWebClient;
    private final MessageService messageService;

    @Override
    public Class<CallbackDtoSubscribed> getCallbackType() {
        return CallbackDtoSubscribed.class;
    }

    @Override
    public SubscribeDtoResponse handleEvent(CallbackDtoSubscribed request) {
        userService.subscribe(request.getUser().getId());
        MessageProcessContext context = new MessageProcessContext(request.getUser().getId(), chatbotProperties.getBotName(), chatbotProperties.getBotAvatar(), chatbotProperties.getMinApiVersion(), null, null);
        MessageRequest messageRequest = messageService.getMessageRequest(context);
        viberWebClient.sendMessage(messageRequest);
        return null;
    }
}
