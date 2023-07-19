package com.sharetreats01.viber_chatbot.message.dto;

import com.sharetreats01.viber_chatbot.message.enums.RichMediaButtonPropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductDetailButtonProperty {
    private RichMediaButtonPropertyType type;
    private List<String> textValues;
    private String actionBody;
    private String image;

    public static ProductDetailButtonProperty createImage(String image) {
        return new ProductDetailButtonProperty(RichMediaButtonPropertyType.IMAGE, null, null, image);
    }

    public static ProductDetailButtonProperty createContent(String productName, String price) {
        return new ProductDetailButtonProperty(RichMediaButtonPropertyType.CONTENT, List.of(productName, price), null, null);
    }

    public static ProductDetailButtonProperty createButton(String productId) {
        return new ProductDetailButtonProperty(RichMediaButtonPropertyType.BUTTON, null, productId, null);
    }
}
