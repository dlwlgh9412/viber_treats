package com.sharetreats01.viber_chatbot.viber.dto.response;

import com.sharetreats01.viber_chatbot.viber.enums.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class SendMessageResponse {
    private Status status;
    private Status statusMessage;
    private String messageToken;
    private String chatHostname;
    private Integer billingStatus;
}
