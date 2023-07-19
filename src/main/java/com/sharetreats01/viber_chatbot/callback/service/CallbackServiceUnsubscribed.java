package com.sharetreats01.viber_chatbot.callback.service;

import com.sharetreats01.viber_chatbot.callback.dto.callback.request.CallbackUnsubscribed;
import com.sharetreats01.viber_chatbot.callback.dto.callback.response.UnsubscribeDtoResponse;
import com.sharetreats01.viber_chatbot.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CallbackServiceUnsubscribed implements CallbackService<CallbackUnsubscribed, UnsubscribeDtoResponse> {
    private final UserService userService;

    @Override
    public Class<CallbackUnsubscribed> getCallbackType() {
        return CallbackUnsubscribed.class;
    }

    @Override
    public UnsubscribeDtoResponse handleEvent(CallbackUnsubscribed request) {
        userService.unsubscribe(request.getUserId());
        return null;
    }
}
