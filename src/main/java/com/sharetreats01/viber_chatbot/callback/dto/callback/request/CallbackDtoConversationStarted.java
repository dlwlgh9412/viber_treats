package com.sharetreats01.viber_chatbot.callback.dto.callback.request;

import com.sharetreats01.viber_chatbot.callback.dto.callback.request.property.User;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CallbackDtoConversationStarted extends CallbackDto {
    private String type;
    private String context;
    private User user;
    private Boolean subscribed;
}
