package com.sharetreats01.viber_chatbot.sharetreats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TreatFriendDto {
    private String recipient;
    private String yourInfo;
    private String message;
    private String promoCode;
    private String paymentOption;
}
