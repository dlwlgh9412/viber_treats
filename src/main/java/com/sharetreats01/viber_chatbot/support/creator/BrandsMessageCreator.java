package com.sharetreats01.viber_chatbot.support.creator;

import com.sharetreats01.viber_chatbot.dto.callback.request.MessageRequest;
import com.sharetreats01.viber_chatbot.dto.callback.request.SubscribedRequest;
import com.sharetreats01.viber_chatbot.infra.viber.dto.request.SendMessageRequest;
import com.sharetreats01.viber_chatbot.infra.viber.dto.request.SendTextMessageRequest;
import com.sharetreats01.viber_chatbot.infra.viber.service.KeyBoardService;
import com.sharetreats01.viber_chatbot.properties.ChatbotProperties;
import com.sharetreats01.viber_chatbot.service.MessageService;
import com.sharetreats01.viber_chatbot.util.TrackingDataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandsMessageCreator {
    private final TrackingDataUtils trackingDataUtils;
    private final KeyBoardService keyBoardService;
    private final MessageService messageService;
    private final ChatbotProperties chatbotProperties;

    protected String createTrackingData() {
        return trackingDataUtils.createTrackingData();
    }

    private String getReceiverId(MessageRequest request) {
        if (request instanceof SubscribedRequest) {
            return ((SubscribedRequest) request).getUser().getId();
        }
        return request.getSender().getId();
    }

    public SendMessageRequest createMessageRequest(MessageRequest request) {
        String receiver = getReceiverId(request);
        String trackingData = createTrackingData();
        SendTextMessageRequest textMessageRequest = new SendTextMessageRequest(receiver, chatbotProperties.getBotName(), chatbotProperties.getBotAvatar(), messageService.findBrandsMessage(), trackingData);
        textMessageRequest.setKeyboard(keyBoardService.findBrands());

        return textMessageRequest;
    }
}
