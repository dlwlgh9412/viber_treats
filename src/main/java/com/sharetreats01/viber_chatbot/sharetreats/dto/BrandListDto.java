package com.sharetreats01.viber_chatbot.sharetreats.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandListDto {
    private List<Data> data;

    @Getter
    public static class Data {
        private final Long id;
        private final String name;
        private final String image;

        public Data(Long id, String name, String image) {
            this.id = id;
            this.name = name;
            this.image = image;
        }
    }
}
