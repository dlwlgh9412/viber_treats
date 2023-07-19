package com.sharetreats01.viber_chatbot.callback.dto.callback.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CallbackDtoFailed extends CallbackDto {
    private String userId;
    private String desc;
}
