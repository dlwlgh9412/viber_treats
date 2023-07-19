package com.sharetreats01.viber_chatbot.message.service;

import com.sharetreats01.viber_chatbot.message.dto.MessageProcessContext;
import com.sharetreats01.viber_chatbot.viber.dto.request.MessageRequest;

public interface MessageService {
    MessageRequest getMessageRequest(MessageProcessContext context);
}
