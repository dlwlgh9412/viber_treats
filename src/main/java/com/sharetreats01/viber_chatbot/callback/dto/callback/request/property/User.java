package com.sharetreats01.viber_chatbot.callback.dto.callback.request.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private String id;
    private String name;
    private String avatar;
    private String country;
    private String language;
    @JsonProperty("api_version")
    private Integer apiVersion;
}
