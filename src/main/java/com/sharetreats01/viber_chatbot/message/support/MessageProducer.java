package com.sharetreats01.viber_chatbot.message.support;

import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import com.sharetreats01.viber_chatbot.message.dto.MessageProcessContext;
import com.sharetreats01.viber_chatbot.viber.dto.request.MessageRequest;

public interface MessageProducer {
    MessageState getType();
    MessageRequest produceMessage(MessageProcessContext context);
}
