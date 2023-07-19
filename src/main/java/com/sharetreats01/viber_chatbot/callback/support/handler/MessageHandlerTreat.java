package com.sharetreats01.viber_chatbot.callback.support.handler;

import com.sharetreats01.viber_chatbot.callback.dto.MessageRequestContext;
import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import com.sharetreats01.viber_chatbot.callback.enums.TreatState;
import com.sharetreats01.viber_chatbot.callback.utils.TreatMessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageHandlerTreat implements MessageHandler {
    private final Map<TreatState, TreatHandler> treatMessageHandlers;
    private final TreatMessageUtils treatMessageUtils;

    @Override
    public MessageState getMessageHandleType() {
        return MessageState.TREAT;
    }

    @Override
    public void handle(MessageRequestContext context) {
        log.info("Message Handle Treat");
        context.setTreatHandlePathKey(treatMessageUtils.extractHandleKey(context));
        TreatHandler handler = treatMessageHandlers.get(context.getTreatHandlePathKey());
        if (handler != null) handler.handle(context);
    }
}
