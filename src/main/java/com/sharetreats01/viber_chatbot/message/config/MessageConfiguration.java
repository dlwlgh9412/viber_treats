package com.sharetreats01.viber_chatbot.message.config;

import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import com.sharetreats01.viber_chatbot.message.support.MessageProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class MessageConfiguration {
    @Bean
    public Map<MessageState, MessageProducer> messageProducers(List<MessageProducer> producers) {
        return producers.stream().collect(Collectors.toMap(MessageProducer::getType, Function.identity()));
    }
}
