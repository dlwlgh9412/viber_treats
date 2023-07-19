package com.sharetreats01.viber_chatbot.callback.support.handler;

import com.sharetreats01.viber_chatbot.callback.dto.MessageRequestContext;
import com.sharetreats01.viber_chatbot.callback.enums.MessageState;

public interface MessageHandler {
    MessageState getMessageHandleType();
    void handle(MessageRequestContext context);
}
