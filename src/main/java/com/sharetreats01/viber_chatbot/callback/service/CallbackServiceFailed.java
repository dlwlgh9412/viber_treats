package com.sharetreats01.viber_chatbot.callback.service;

import com.sharetreats01.viber_chatbot.callback.dto.callback.request.CallbackDtoFailed;
import com.sharetreats01.viber_chatbot.callback.dto.callback.response.FailedDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CallbackServiceFailed implements CallbackService<CallbackDtoFailed, FailedDtoResponse> {
    @Override
    public Class<CallbackDtoFailed> getCallbackType() {
        return CallbackDtoFailed.class;
    }

    @Override
    public FailedDtoResponse handleEvent(CallbackDtoFailed request) {
        return null;
    }
}
