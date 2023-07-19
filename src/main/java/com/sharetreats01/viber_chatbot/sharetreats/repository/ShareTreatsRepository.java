package com.sharetreats01.viber_chatbot.sharetreats.repository;

import com.sharetreats01.viber_chatbot.sharetreats.dto.BrandListDto;
import com.sharetreats01.viber_chatbot.sharetreats.dto.ProductDetailDto;
import com.sharetreats01.viber_chatbot.sharetreats.dto.ProductListDto;

public interface ShareTreatsRepository {
    BrandListDto findBrandListDto();
    ProductListDto findProductListDtoByBrandId(Long brandId);

    ProductDetailDto findProductDetailDtoById(Long productId);
}
