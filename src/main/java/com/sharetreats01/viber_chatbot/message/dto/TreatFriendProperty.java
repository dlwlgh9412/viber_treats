package com.sharetreats01.viber_chatbot.message.dto;

import com.sharetreats01.viber_chatbot.message.enums.RichMediaButtonPropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TreatFriendProperty {
    private RichMediaButtonPropertyType type;
    private List<String> textValues;
    private String actionBody;
    private String productImage;

    public static TreatFriendProperty createImage(String image) {
        return new TreatFriendProperty(RichMediaButtonPropertyType.IMAGE, null, null, image);
    }

    public static TreatFriendProperty createContent(String productName, String price, String recipient, String yourInfo, String message, String code, String paymentOption) {
        return new TreatFriendProperty(RichMediaButtonPropertyType.CONTENT, List.of(productName, price, recipient, yourInfo, message, code, paymentOption), null, null);
    }

    public static TreatFriendProperty createButton() {
        return new TreatFriendProperty(RichMediaButtonPropertyType.BUTTON, null, null, null);
    }
}
