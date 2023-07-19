package com.sharetreats01.viber_chatbot.message.util;

import com.sharetreats01.viber_chatbot.message.dto.*;
import com.sharetreats01.viber_chatbot.message.entity.ViberRichMediaButtonEntity;
import com.sharetreats01.viber_chatbot.sharetreats.dto.BrandListDto;
import com.sharetreats01.viber_chatbot.sharetreats.dto.ProductDetailDto;
import com.sharetreats01.viber_chatbot.sharetreats.dto.ProductListDto;
import com.sharetreats01.viber_chatbot.sharetreats.dto.TreatFriendDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessagePropertyUtils {
    public static List<ButtonPropDto> ConvertToButtonsPropDtoList(BrandListDto brandListDto) {
        return brandListDto.getData().stream()
                .map(dto -> new ButtonPropDto(List.of(dto.getName()), dto.getId().toString(), dto.getImage()))
                .collect(Collectors.toList());
    }

    public static List<ViberRichMediaButtonEntity> ConvertToProductDetailRichMediaButtonList(List<ViberRichMediaButtonEntity> buttons, ProductListDto productListDto) {
        List<ProductListButtonProperty> properties = new ArrayList<>();
        for (ProductListDto.Data data : productListDto.getData()) {
            properties.add(ProductListButtonProperty.createImage(data.getImage()));
            properties.add(ProductListButtonProperty.createContent(data.getName(), data.getBrandName(), data.getAmount().toString()));
            properties.add(ProductListButtonProperty.createButton());
            properties.add(ProductListButtonProperty.createMoreDetails(data.getId().toString()));
        }

        List<ViberRichMediaButtonEntity> result = new ArrayList<>();
        for (int i = 0; i < properties.size(); i++) {
            ProductListButtonProperty property = properties.get(i);
            ViberRichMediaButtonEntity button = buttons.get(i % buttons.size());
            result.add(button.createEntityOnProductListButtonProperty(property));
        }
        return result;
    }

    public static List<ViberRichMediaButtonEntity> ConvertToProductDetailRichMediaButtonList(List<ViberRichMediaButtonEntity> buttons, ProductDetailDto productDetailDto) {
        List<ProductDetailButtonProperty> properties = new ArrayList<>();
        properties.add(ProductDetailButtonProperty.createImage(productDetailDto.getProductImage()));
        properties.add(ProductDetailButtonProperty.createContent(productDetailDto.getProductName(), productDetailDto.getAmount().toString()));
        properties.add(ProductDetailButtonProperty.createButton());

        List<ViberRichMediaButtonEntity> result = new ArrayList<>();
        for (int i = 0; i < properties.size(); i++) {
            ProductDetailButtonProperty property = properties.get(i);
            ViberRichMediaButtonEntity button = buttons.get(i % buttons.size());
            result.add(button.createEntityOnProductDetailButtonProperty(property));
        }
        return result;
    }

    public static List<ViberRichMediaButtonEntity> ConvertToTreatFriendRichMediaButtonList(List<ViberRichMediaButtonEntity> buttons, List<TreatFriendProperty> properties) {
        List<ViberRichMediaButtonEntity> result = new ArrayList<>();
        for (int i = 0; i < properties.size(); i++) {
            TreatFriendProperty property = properties.get(i);
            ViberRichMediaButtonEntity button = buttons.get(i % buttons.size());
            result.add(button.createEntityOnTreatFriendButtonProperty(property));
        }
        return result;
    }

    public static List<ViberRichMediaButtonEntity> ConvertToTreatMeRichMediaButtonList(List<ViberRichMediaButtonEntity> buttons, List<TreatMeProperty> properties) {
        List<ViberRichMediaButtonEntity> result = new ArrayList<>();
        for (int i = 0; i < properties.size(); i++) {
            TreatMeProperty property = properties.get(i);
            ViberRichMediaButtonEntity button = buttons.get(i % buttons.size());
            result.add(button.createEntityOnTreatMeButtonProperty(property));
        }
        return result;
    }
}
