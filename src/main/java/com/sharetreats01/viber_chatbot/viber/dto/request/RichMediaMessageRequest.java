package com.sharetreats01.viber_chatbot.viber.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sharetreats01.viber_chatbot.message.entity.ViberRichMediaEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RichMediaMessageRequest extends MessageRequest {
    private final String type = "rich_media";
    private final String text;

    @JsonProperty("rich_media")
    private ViberRichMediaEntity richMedia;

    public RichMediaMessageRequest(String receiver, String trackingData, Integer minApiVersion, String text, ViberRichMediaEntity richMedia) {
        super(receiver, trackingData, minApiVersion);
        this.text = text;
        this.richMedia = richMedia;
    }
}
