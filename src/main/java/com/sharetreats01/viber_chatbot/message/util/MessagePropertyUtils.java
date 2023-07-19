package com.sharetreats01.viber_chatbot.message.util;

import com.sharetreats01.viber_chatbot.message.dto.ButtonPropDto;
import com.sharetreats01.viber_chatbot.message.dto.ProductListButtonProperty;
import com.sharetreats01.viber_chatbot.message.entity.ViberRichMediaButtonEntity;
import com.sharetreats01.viber_chatbot.sharetreats.dto.BrandListDto;
import com.sharetreats01.viber_chatbot.sharetreats.dto.ProductDetailDto;
import com.sharetreats01.viber_chatbot.sharetreats.dto.ProductListDto;
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

    public static List<ViberRichMediaButtonEntity> ConvertToProductsRichMediaButtonList(List<ViberRichMediaButtonEntity> buttons, ProductListDto productListDto) {
        List<ProductListButtonProperty> properties = new ArrayList<>();
        for (ProductListDto.Data data : productListDto.getData()) {
            properties.add(ProductListButtonProperty.createImage(data.getImage()));
            properties.add(ProductListButtonProperty.createContent(data.getName(), data.getBrandName(), data.getAmount().toString()));
            properties.add(ProductListButtonProperty.createButton(data.getId().toString()));
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

    public static List<ButtonPropDto> ConvertToButtonsPropDtoList(ProductDetailDto productDetailDto) {
        return null;
    }
}
