package com.sharetreats01.viber_chatbot.sharetreats.service;

import com.sharetreats01.viber_chatbot.sharetreats.dto.BrandListDto;
import com.sharetreats01.viber_chatbot.sharetreats.dto.ProductDetailDto;
import com.sharetreats01.viber_chatbot.sharetreats.dto.ProductListDto;

public interface ShareTreatsService {
    BrandListDto getBrandListDto();
    ProductListDto getProductListDtoByBrandId(String brandId);
    ProductDetailDto getProductDetailDtoByProductId(String productId);
}
