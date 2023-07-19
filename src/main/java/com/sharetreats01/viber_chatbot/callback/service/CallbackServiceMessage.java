package com.sharetreats01.viber_chatbot.callback.service;

import com.sharetreats01.viber_chatbot.callback.dto.MessageRequestContext;
import com.sharetreats01.viber_chatbot.callback.dto.callback.request.CallbackDtoMessage;
import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import com.sharetreats01.viber_chatbot.callback.dto.callback.response.MessageDtoResponse;
import com.sharetreats01.viber_chatbot.callback.enums.TreatState;
import com.sharetreats01.viber_chatbot.callback.utils.MessageUtils;
import com.sharetreats01.viber_chatbot.callback.support.handler.MessageHandler;

import com.sharetreats01.viber_chatbot.callback.utils.TreatMessageUtils;
import com.sharetreats01.viber_chatbot.common.properties.ChatbotProperties;
import com.sharetreats01.viber_chatbot.message.dto.MessageProcessContext;
import com.sharetreats01.viber_chatbot.message.service.MessageService;
import com.sharetreats01.viber_chatbot.viber.client.ViberWebClient;
import com.sharetreats01.viber_chatbot.viber.dto.request.MessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CallbackServiceMessage implements CallbackService<CallbackDtoMessage, MessageDtoResponse> {
    private final Map<MessageState, MessageHandler> handlers;
    private final MessageUtils messageUtils;
    private final TreatMessageUtils treatMessageUtils;
    private final ChatbotProperties chatbotProperties;
    private final MessageService messageService;
    private final ViberWebClient viberWebClient;

    @Override
    public Class<CallbackDtoMessage> getCallbackType() {
        return CallbackDtoMessage.class;
    }

    @Override
    public MessageDtoResponse handleEvent(CallbackDtoMessage request) {
        MessageRequestContext requestContext = MessageRequestContext.create(request.getSender().getId(), chatbotProperties.getBotName(), chatbotProperties.getBotAvatar(), chatbotProperties.getMinApiVersion(), request.getMessage().getText(), request.getMessage().getTrackingData());
        requestContext.setMessageHandlePathKey(messageUtils.extractHandleKey(requestContext.getTrackingData(), requestContext.getInput()));
        MessageHandler messageHandler = handlers.get(requestContext.getMessageHandlePathKey());

        if (messageHandler != null) messageHandler.handle(requestContext);

        MessageState messageState = messageUtils.extractMessageKey(requestContext.getTrackingData(), requestContext.getInput());
        TreatState treatState = null;
        if (messageUtils.isTreatProcess(requestContext.getTrackingData(), requestContext.getInput()))
            treatState = treatMessageUtils.extractNextMessageKey(requestContext);
        MessageProcessContext messageProcessContext = new MessageProcessContext(requestContext.getReceiverId(), requestContext.getSenderName(), requestContext.getSenderAvatar(), requestContext.getMinApiVersion(), requestContext.getInput(), requestContext.getTrackingData(), messageState, treatState);
        MessageRequest messageRequest = messageService.getMessageRequest(messageProcessContext);
        viberWebClient.sendMessage(messageRequest);
        return null;
    }
}