package com.sharetreats01.viber_chatbot.callback.dto.callback.request;

import com.sharetreats01.viber_chatbot.callback.dto.callback.request.property.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CallbackDtoSubscribed extends CallbackDtoMessage {
    private User user;
}
