package com.sharetreats01.viber_chatbot.message.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ButtonPropDto {
    private String text;
    private String actionBody;
    private String image;
}
