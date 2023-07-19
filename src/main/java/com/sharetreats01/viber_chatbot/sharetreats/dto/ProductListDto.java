package com.sharetreats01.viber_chatbot.sharetreats.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductListDto {
    private List<Data> data;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Data {
        private String name;
        private Integer amount;
        private String image;

        public Data(String name, Integer amount, String image) {
            this.name = name;
            this.amount = amount;
            this.image = image;
        }
    }
}
