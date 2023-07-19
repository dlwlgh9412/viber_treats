package com.sharetreats01.viber_chatbot.message.dto;

import com.sharetreats01.viber_chatbot.message.enums.RichMediaButtonPropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TreatMeProperty {
    private RichMediaButtonPropertyType type;
    private List<String> textValues;
    private String actionBody;
    private String image;

    public static TreatMeProperty createImage(String image) {
        return new TreatMeProperty(RichMediaButtonPropertyType.IMAGE, null, null, image);
    }

    public static TreatMeProperty createContent(String productName, String price, String youInfo, String code, String paymentOption) {
        return new TreatMeProperty(RichMediaButtonPropertyType.CONTENT, List.of(productName, price, youInfo, code, paymentOption), null, null);
    }

    public static TreatMeProperty createButton() {
        return new TreatMeProperty(RichMediaButtonPropertyType.BUTTON, null, null, null);
    }
}
