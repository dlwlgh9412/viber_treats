package com.sharetreats01.viber_chatbot.message.dto;

import com.sharetreats01.viber_chatbot.message.enums.RichMediaButtonPropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductListButtonProperty {
    private RichMediaButtonPropertyType type;
    private List<String> textValues;
    private String actionBody;
    private String image;

    public static ProductListButtonProperty createImage(String image) {
        return new ProductListButtonProperty(RichMediaButtonPropertyType.IMAGE, null, null, image);
    }

    public static ProductListButtonProperty createContent(String productName, String brandName, String price) {
        return new ProductListButtonProperty(RichMediaButtonPropertyType.CONTENT, List.of(productName, brandName, price), null, null);
    }

    public static ProductListButtonProperty createButton(String productId) {
        return new ProductListButtonProperty(RichMediaButtonPropertyType.BUTTON, null, productId, null);
    }

    public static ProductListButtonProperty createMoreDetails(String productId) {
        return new ProductListButtonProperty(RichMediaButtonPropertyType.DETAIL, null, productId, null);
    }
}
