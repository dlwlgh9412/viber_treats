package com.sharetreats01.viber_chatbot.sharetreats.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tb_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    @ToString.Exclude
    private BrandEntity brand;

    @Column(name = "product_name", length = 100, nullable = false)
    private String productName;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "product_image", nullable = false)
    private String productImage;

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }
}
