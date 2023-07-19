package com.sharetreats01.viber_chatbot.sharetreats.repository;

import com.sharetreats01.viber_chatbot.sharetreats.dto.BrandListDto;
import com.sharetreats01.viber_chatbot.sharetreats.dto.ProductDetailDto;
import com.sharetreats01.viber_chatbot.sharetreats.dto.ProductListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShareTreatsRepositoryImpl implements ShareTreatsRepository {
    private final EntityManager em;


    @Override
    public BrandListDto findBrandListDto() {
        List<BrandListDto.Data> result = em.createQuery(
                        "select new com.sharetreats01.viber_chatbot.sharetreats.dto.BrandListDto$Data(b.id, b.brandName, b.brandImage) " +
                                "from BrandEntity b", BrandListDto.Data.class)
                .getResultList();
        return new BrandListDto(result);
    }

    @Override
    public ProductListDto findProductListDtoByBrandId(Long brandId) {
        List<ProductListDto.Data> result = em.createQuery(
                        "select new com.sharetreats01.viber_chatbot.sharetreats.dto.ProductListDto$Data(p.id, p.productName, p.brand.brandName, p.amount, p.productImage) " +
                                "from ProductEntity p " +
                                "where p.brand.id =: brandId",
                        ProductListDto.Data.class)
                .setParameter("brandId", brandId).getResultList();
        return new ProductListDto(result);
    }

    @Override
    public ProductDetailDto findProductDetailDtoById(Long productId) {
        return em.createQuery(
                        "select new com.sharetreats01.viber_chatbot.sharetreats.dto.ProductDetailDto(p.id, p.productName, p.productImage, p.brand.id, p.amount) " +
                                "from ProductEntity p " +
                                "where p.id =: productId",
                        ProductDetailDto.class)
                .setParameter("productId", productId)
                .getResultList().stream().findFirst().orElseThrow();
    }
}
