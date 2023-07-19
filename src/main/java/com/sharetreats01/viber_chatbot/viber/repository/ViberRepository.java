package com.sharetreats01.viber_chatbot.viber.repository;


import com.sharetreats01.viber_chatbot.viber.entity.ViberWebhookSettingEntity;


public interface ViberRepository {
    ViberWebhookSettingEntity findRecentWebhookSetting();
}
