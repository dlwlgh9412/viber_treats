package com.sharetreats01.viber_chatbot.message.service;

import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import com.sharetreats01.viber_chatbot.callback.enums.TreatState;
import com.sharetreats01.viber_chatbot.message.dto.MessageProcessContext;
import com.sharetreats01.viber_chatbot.message.support.MessageProducer;
import com.sharetreats01.viber_chatbot.message.support.TreatMessageProducer;
import com.sharetreats01.viber_chatbot.viber.dto.request.MessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService {
    private final Map<MessageState, MessageProducer> messageProducers;
    private final Map<TreatState, TreatMessageProducer> treatMessageProducers;

    @Override
    public MessageRequest getMessageRequest(MessageProcessContext context) {
        MessageRequest messageRequest;
        if (context.getMessageState() != null && context.getMessageState() != MessageState.TREAT) {
            MessageProducer producer = messageProducers.get(context.getMessageState());
            messageRequest = producer.produceMessage(context);
        } else {
            TreatMessageProducer producer = treatMessageProducers.get(context.getTreatState());
            messageRequest = producer.produceMessage(context);
        }

        return messageRequest;
    }
}
