package com.sharetreats01.viber_chatbot.message.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ButtonPropDto {
    private List<String> textValues;
    private String actionBody;
    private String image;
}
