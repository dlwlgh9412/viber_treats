package com.sharetreats01.viber_chatbot.callback.support.handler;

import com.sharetreats01.viber_chatbot.callback.dto.MessageRequestContext;
import com.sharetreats01.viber_chatbot.callback.enums.TreatState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class TreatHandlerRecipient implements TreatHandler {

    @Override
    public TreatState getConstantsType() {
        return TreatState.RECIPIENT;
    }

    @Override
    public void handle(MessageRequestContext context) {
        log.info("Treat Recipient input: {} tracking: {}", context.getInput(), context.getTrackingData());
    }
}
