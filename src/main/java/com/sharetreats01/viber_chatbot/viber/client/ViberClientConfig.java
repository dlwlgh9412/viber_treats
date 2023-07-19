package com.sharetreats01.viber_chatbot.viber.client;

import com.sharetreats01.viber_chatbot.viber.properties.ViberProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
@RequiredArgsConstructor
public class ViberClientConfig {
    private final ViberProperties viberProperties;

    @Bean
    public WebClient viberWebClient() {
        return WebClient.builder()
                .baseUrl(viberProperties.getViberApiUrl())
                .defaultHeader(viberProperties.getViberAuthHeader(), viberProperties.getViberAuthToken())
                .build();
    }
}
