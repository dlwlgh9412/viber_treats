package com.sharetreats01.viber_chatbot.callback.service;

import com.sharetreats01.viber_chatbot.callback.dto.callback.request.CallbackDtoDelivered;
import com.sharetreats01.viber_chatbot.callback.dto.callback.response.DeliveredDtoResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CallbackServiceDelivered implements CallbackService<CallbackDtoDelivered, DeliveredDtoResponseMessage> {
    @Override
    public Class<CallbackDtoDelivered> getCallbackType() {
        return CallbackDtoDelivered.class;
    }

    @Override
    public DeliveredDtoResponseMessage handleEvent(CallbackDtoDelivered request) {
        return null;
    }
}
