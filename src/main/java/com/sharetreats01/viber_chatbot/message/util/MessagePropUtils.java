package com.sharetreats01.viber_chatbot.message.util;

import com.sharetreats01.viber_chatbot.message.dto.ButtonPropDto;
import com.sharetreats01.viber_chatbot.sharetreats.dto.BrandListDto;
import com.sharetreats01.viber_chatbot.sharetreats.dto.ProductDetailDto;
import com.sharetreats01.viber_chatbot.sharetreats.dto.ProductListDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessagePropUtils {
    private static final String DELIMITER = "_";
    public static List<ButtonPropDto> ConvertToButtonsPropDtoList(BrandListDto brandListDto) {
        return brandListDto.getData().stream()
                .map(dto -> new ButtonPropDto(dto.getName(), dto.getName() + DELIMITER + dto.getId(), dto.getImage()))
                .collect(Collectors.toList());
    }

    public static List<ButtonPropDto> ConvertToButtonsPropDtoList(ProductListDto productListDto) {
        return null;
//        return productListDto.getData().stream()
//                .map(dto -> new ButtonPropDto(dto.getName(), dto.getName(), dto.getImage()))
//                .collect(Collectors.toList());
    }

    public static List<ButtonPropDto> ConvertToButtonsPropDtoList(ProductDetailDto productDetailDto) {
        return null;
    }
}
