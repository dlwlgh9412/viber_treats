package com.sharetreats01.viber_chatbot.callback.dto.callback.request.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sharetreats01.viber_chatbot.common.enums.Type;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class Message {
    private Type type;
    private String text;
    private String media;
    private Location location;
    private Contact contact;
    @JsonProperty("tracking_data")
    private String trackingData;
    @JsonProperty("file_name")
    private String fileName;
    @JsonProperty("file_size")
    private Integer fileSize;
    private Integer duration;
    @JsonProperty("sticker_id")
    private Integer stickerId;

    public void setTrackingData(String trackingData) {
        this.trackingData = trackingData;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Location {
        private String lat;
        private String lon;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Contact {
        @JsonProperty("name")
        private String name;
        @JsonProperty("phone_number")
        private String phoneNumber;
    }
}
