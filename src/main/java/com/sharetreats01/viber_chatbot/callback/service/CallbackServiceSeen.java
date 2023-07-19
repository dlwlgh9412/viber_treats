package com.sharetreats01.viber_chatbot.callback.service;

import com.sharetreats01.viber_chatbot.callback.dto.callback.request.CallbackDtoSeen;
import com.sharetreats01.viber_chatbot.callback.dto.callback.response.SeenDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CallbackServiceSeen implements CallbackService<CallbackDtoSeen, SeenDtoResponse> {
    @Override
    public Class<CallbackDtoSeen> getCallbackType() {
        return CallbackDtoSeen.class;
    }

    @Override
    public SeenDtoResponse handleEvent(CallbackDtoSeen request) {
        return null;
    }
}
