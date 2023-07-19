package com.sharetreats01.viber_chatbot.sharetreats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TreatMeDto {
    private String yourInfo;
    private String promoCode;
    private String paymentOption;
}
