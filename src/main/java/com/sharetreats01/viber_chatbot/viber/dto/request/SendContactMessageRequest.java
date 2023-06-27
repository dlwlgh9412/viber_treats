package com.sharetreats01.viber_chatbot.viber.dto.request;

import com.sharetreats01.viber_chatbot.interaction.dto.callback.parameter.Contact;

import lombok.Getter;

@Getter
public class SendContactMessageRequest extends SendMessageRequest{
    private final String type = "contact";
    private final Contact contact;

    public SendContactMessageRequest(String receiver, String senderName, String senderAvatar, String trackingData, Integer minApiVersion, Contact contact) {
        super(receiver, senderName, senderAvatar, trackingData, minApiVersion);
        this.contact = contact;
    }
}