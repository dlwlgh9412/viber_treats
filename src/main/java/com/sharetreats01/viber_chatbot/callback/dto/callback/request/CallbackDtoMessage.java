package com.sharetreats01.viber_chatbot.callback.dto.callback.request;

import com.sharetreats01.viber_chatbot.callback.dto.callback.request.property.Message;
import com.sharetreats01.viber_chatbot.callback.dto.callback.request.property.Sender;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class CallbackDtoMessage extends CallbackDto {
    private Sender sender;
    private Message message;
}
