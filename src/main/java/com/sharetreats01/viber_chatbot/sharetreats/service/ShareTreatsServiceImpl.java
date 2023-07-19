package com.sharetreats01.viber_chatbot.sharetreats.service;

import com.sharetreats01.viber_chatbot.sharetreats.dto.BrandListDto;
import com.sharetreats01.viber_chatbot.sharetreats.dto.ProductDetailDto;
import com.sharetreats01.viber_chatbot.sharetreats.dto.ProductListDto;
import com.sharetreats01.viber_chatbot.sharetreats.repository.ShareTreatsRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShareTreatsServiceImpl implements ShareTreatsService {
    private final ShareTreatsRepository repository;

    @Override
    public BrandListDto getBrandListDto() {
        return repository.findBrandListDto();
    }

    @Override
    public ProductListDto getProductListDtoByBrandId(String brandId) {
        return repository.findProductListDtoByBrandId(brandId);
    }

    @Override
    public ProductDetailDto getProductDetailDtoByProductId(String productId) {
        return repository.findProductDetailDtoById(productId);
    }
}
