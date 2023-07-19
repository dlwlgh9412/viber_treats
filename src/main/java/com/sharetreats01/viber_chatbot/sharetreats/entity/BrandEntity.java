package com.sharetreats01.viber_chatbot.sharetreats.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tb_brand")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_name", nullable = false, length = 100)
    private String brandName;

    @Column(name = "brand_image")
    private String brandImage;
}
