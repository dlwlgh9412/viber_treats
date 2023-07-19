package com.sharetreats01.viber_chatbot.callback.support.handler;

import com.sharetreats01.viber_chatbot.callback.dto.MessageRequestContext;
import com.sharetreats01.viber_chatbot.callback.enums.TreatState;

public interface TreatHandler {
    TreatState getConstantsType();

    void handle(MessageRequestContext context);
}
