package com.sharetreats01.viber_chatbot.message.support;

import com.sharetreats01.viber_chatbot.callback.enums.TreatState;
import com.sharetreats01.viber_chatbot.callback.utils.TreatMessageUtils;
import com.sharetreats01.viber_chatbot.message.dto.MessageProcessContext;
import com.sharetreats01.viber_chatbot.viber.dto.request.MessageRequest;
import com.sharetreats01.viber_chatbot.viber.dto.request.TextMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TreatMessageProducerMessage implements TreatMessageProducer {
    private final TreatMessageUtils treatMessageUtils;

    @Override
    public TreatState getConstantType() {
        return TreatState.MESSAGE;
    }

    @Override
    public MessageRequest produceMessage(MessageProcessContext context) {
        log.info("Message Produce Treat Message");
        return TextMessageRequest.createDefault(context.getReceiverId(), context.getSenderName(), context.getSenderAvatar(), context.getMinApiVersion(), treatMessageUtils.pasteInputData(context.getTrackingData(), context.getInput()), "Enter Message");
    }
}
