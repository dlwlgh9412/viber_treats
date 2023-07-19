package com.sharetreats01.viber_chatbot.message.support;

import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import com.sharetreats01.viber_chatbot.message.dto.MessageProcessContext;
import com.sharetreats01.viber_chatbot.viber.dto.request.MessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageProducerProductDetail implements MessageProducer {
    @Override
    public MessageState getType() {
        return MessageState.DETAIL;
    }

    @Override
    public MessageRequest produceMessage(MessageProcessContext context) {
        log.info("Message Produce Product Detail");
        return null;
    }
}
