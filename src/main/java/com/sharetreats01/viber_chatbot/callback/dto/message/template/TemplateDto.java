package com.sharetreats01.viber_chatbot.callback.dto.message.template;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TemplateDto {
    private String template;
    private List<String> placeHolders;
}
