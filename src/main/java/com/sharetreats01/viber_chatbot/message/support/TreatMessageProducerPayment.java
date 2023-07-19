package com.sharetreats01.viber_chatbot.message.support;

import com.sharetreats01.viber_chatbot.callback.enums.TreatState;
import com.sharetreats01.viber_chatbot.callback.utils.TreatMessageUtils;
import com.sharetreats01.viber_chatbot.message.dto.MessageProcessContext;
import com.sharetreats01.viber_chatbot.viber.dto.request.MessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TreatMessageProducerPayment implements TreatMessageProducer {
    private final TreatMessageUtils treatMessageUtils;
    @Override
    public TreatState getConstantType() {
        return TreatState.PAYMENT;
    }

    @Override
    public MessageRequest produceMessage(MessageProcessContext context) {
        log.info("Message Produce Treat Payment");
        // RichMedia Message
        return null;
    }
}
