package com.sharetreats01.viber_chatbot.viber.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sharetreats01.viber_chatbot.viber.dto.request.GetUserDetailsRequest;
import com.sharetreats01.viber_chatbot.viber.properties.ViberProperties;
import com.sharetreats01.viber_chatbot.viber.dto.request.MessageRequest;
import com.sharetreats01.viber_chatbot.viber.dto.response.GetUserDetailsResponse;
import com.sharetreats01.viber_chatbot.viber.dto.response.SendMessageResponse;
import com.sharetreats01.viber_chatbot.viber.dto.request.SetWebhookRequest;
import com.sharetreats01.viber_chatbot.viber.dto.response.SetWebhookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class ViberWebClientImpl implements ViberWebClient {
    private final ObjectMapper objectMapper;
    private final WebClient viberWebClient;
    private final ViberProperties viberProperties;
    private final WebClientResponseResolver responseResolver;


    @Override
    public SetWebhookResponse sendWebhookSetting(SetWebhookRequest request) {
        WebClient.ResponseSpec responseSpec = viberWebClient.post().uri(viberProperties.getSetWebhookUri())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve();
        return responseResolver.messageResolve(responseSpec, SetWebhookResponse.class);
    }

    @Override
    public SendMessageResponse sendMessage(MessageRequest request) {
        try {
            log.info("{}", objectMapper.writeValueAsString(request));
            log.info("{}", request.getTrackingData());
        } catch (Exception e) {

        }
        WebClient.ResponseSpec responseSpec = viberWebClient.post().uri(viberProperties.getSendMessageUri())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve();
        SendMessageResponse response = responseResolver.messageResolve(responseSpec, SendMessageResponse.class);
        ViberWebClientResponseResolver.handleSendMessageResponse(response);
        return response;
    }

    @Override
    public GetUserDetailsResponse getUserDetails(GetUserDetailsRequest request) {
        WebClient.ResponseSpec responseSpec = viberWebClient.post().uri(viberProperties.getGetUserDetailsUri())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve();
        return responseResolver.messageResolve(responseSpec, GetUserDetailsResponse.class);
    }
}
