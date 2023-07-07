package com.sharetreats01.viber_chatbot.infra.viber.sender;

import com.sharetreats01.viber_chatbot.dto.callback.request.MessageRequest;
import com.sharetreats01.viber_chatbot.dto.callback.request.property.State;
import com.sharetreats01.viber_chatbot.infra.viber.client.ViberWebClient;
import com.sharetreats01.viber_chatbot.infra.viber.dto.request.SendMessageRequest;
import com.sharetreats01.viber_chatbot.infra.viber.dto.request.SendRichMediaMessageRequest;
import com.sharetreats01.viber_chatbot.properties.ChatbotProperties;
import com.sharetreats01.viber_chatbot.util.TrackingDataUtils;
import com.sharetreats01.viber_chatbot.infra.viber.service.RichMediaService;
import org.springframework.stereotype.Component;

@Component
public class MessageSenderProducts extends AbstractMessageSender implements MessageSender {
    private final RichMediaService richMediaService;

    public MessageSenderProducts(ViberWebClient webClient, ChatbotProperties chatbotProperties, RichMediaService richMediaService) {
        super(webClient, chatbotProperties);
        this.richMediaService = richMediaService;
    }


    @Override
    public State getSenderKey() {
        return State.BRANDS;
    }

    @Override
    public void send(MessageRequest request) {
        super.sendResponse(request);
    }

    @Override
    protected SendMessageRequest createSendMessageRequest(MessageRequest messageRequest) {
        String richMedia = richMediaService.findProductsByBrand(messageRequest.getMessage().getText());
        String trackingData = TrackingDataUtils.updateState(messageRequest.getMessage().getTrackingData(), State.PRODUCTS, messageRequest.getMessage().getText());
        return new SendRichMediaMessageRequest(messageRequest.getSender().getId(), 7, richMedia, trackingData);
    }
}