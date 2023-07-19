package com.sharetreats01.viber_chatbot.sharetreats.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductListDto {
    private List<Data> data;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Data {
        private Long id;
        private String name;
        private String brandName;
        private Integer amount;
        private String image;

        public Data(Long id, String name, String brandName, Integer amount, String image) {
            this.id = id;
            this.name = name;
            this.brandName = brandName;
            this.amount = amount;
            this.image = image;
        }
    }
}
