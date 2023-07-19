package com.sharetreats01.viber_chatbot.sharetreats.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDetailDto {
    private String productName;
    private String productImage;
    private Long brandId;
    private Integer amount;

    public ProductDetailDto(String productName, String productImage, Long brandId, Integer amount) {
        this.productName = productName;
        this.productImage = productImage;
        this.brandId = brandId;
        this.amount = amount;
    }
}
