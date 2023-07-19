package com.sharetreats01.viber_chatbot.callback.service;

import com.sharetreats01.viber_chatbot.callback.dto.callback.request.CallbackDto;
import com.sharetreats01.viber_chatbot.callback.dto.callback.response.CallbackDtoResponse;

public interface CallbackService<T extends CallbackDto, R extends CallbackDtoResponse> {
    Class<T> getCallbackType();

    R handleEvent(T data);
}

