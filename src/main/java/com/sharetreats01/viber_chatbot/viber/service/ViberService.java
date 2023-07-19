package com.sharetreats01.viber_chatbot.viber.service;

import com.sharetreats01.viber_chatbot.viber.client.ViberWebClient;
import com.sharetreats01.viber_chatbot.viber.dto.request.SetWebhookRequest;
import com.sharetreats01.viber_chatbot.viber.dto.response.SetWebhookResponse;
import com.sharetreats01.viber_chatbot.viber.enums.Status;
import com.sharetreats01.viber_chatbot.viber.repository.ViberRepository;
import com.sharetreats01.viber_chatbot.viber.entity.ViberWebhookSettingEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ViberService {
    private final ViberRepository viberRepository;
    private final ViberWebClient webClient;


    @Transactional
    public void setViberWebhook() {
        ViberWebhookSettingEntity entity = viberRepository.findRecentWebhookSetting();
        if (!entity.getIsSet()) {
            SetWebhookRequest request = new SetWebhookRequest(entity.getUrl(), splitEventTypes(entity.getEventTypes()), entity.getSendName(), entity.getSendPhoto());
            SetWebhookResponse response = webClient.sendWebhookSetting(request);

            if (response.getStatus() != Status.OK)
                log.warn("{}", response);
            entity.setComplete();
        }
    }

    private List<String> splitEventTypes(String eventTypes) {
        return Arrays.asList(eventTypes.split(", "));
    }
}
