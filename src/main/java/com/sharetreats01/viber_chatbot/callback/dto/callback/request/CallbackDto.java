package com.sharetreats01.viber_chatbot.callback.dto.callback.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "event")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CallbackDtoConversationStarted.class, name = "conversation_started"),
        @JsonSubTypes.Type(value = CallbackDtoDelivered.class, name = "delivered"),
        @JsonSubTypes.Type(value = CallbackDtoFailed.class, name = "failed"),
        @JsonSubTypes.Type(value = CallbackDtoMessage.class, name = "message"),
        @JsonSubTypes.Type(value = CallbackDtoSeen.class, name = "seen"),
        @JsonSubTypes.Type(value = CallbackDtoSubscribed.class, name = "subscribed"),
        @JsonSubTypes.Type(value = CallbackUnsubscribed.class, name = "unsubscribed")
})
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CallbackDto {
    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonProperty("message_token")
    private Long messageToken;
}
