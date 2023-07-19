package com.sharetreats01.viber_chatbot.callback.config;

import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import com.sharetreats01.viber_chatbot.callback.enums.TreatState;
import com.sharetreats01.viber_chatbot.callback.support.handler.MessageHandler;
import com.sharetreats01.viber_chatbot.callback.support.handler.TreatHandler;
import com.sharetreats01.viber_chatbot.message.support.TreatMessageProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class MessageHandlerConfiguration {
    @Bean
    public Map<MessageState, MessageHandler> messageHandlers(List<MessageHandler> handlers) {
        return handlers.stream().collect(Collectors.toMap(MessageHandler::getMessageHandleType, Function.identity()));
    }

    @Bean
    public Map<MessageState, MessageState> handlerPath() {
        Map<MessageState, MessageState> handlerPath = new HashMap<>();
        handlerPath.put(MessageState.NEW, MessageState.BRANDS);
        handlerPath.put(MessageState.BRANDS, MessageState.PRODUCTS);
        handlerPath.put(MessageState.PRODUCTS, MessageState.DETAIL);
        return handlerPath;
    }

    @Bean
    public Map<TreatState, TreatHandler> treatConstantsHandlers(List<TreatHandler> constantsHandlers) {
        return constantsHandlers.stream().collect(Collectors.toMap(TreatHandler::getConstantsType, Function.identity()));
    }

    @Bean
    public Map<TreatState, TreatMessageProducer> treatMessageHandlers(List<TreatMessageProducer> messageHandlers) {
        return messageHandlers.stream().collect(Collectors.toMap(TreatMessageProducer::getConstantType, Function.identity()));
    }

    @Bean
    public Map<TreatState, List<TreatState>> treatMessagePath() {
        Map<TreatState, List<TreatState>> treatPath = new HashMap<>();
        treatPath.put(TreatState.ME, Arrays.asList(TreatState.YOUR_INFO, TreatState.PROMO_CODE, TreatState.PAYMENT, TreatState.TREAT, TreatState.TREAT_COMPLETE));
        treatPath.put(TreatState.FRIEND, Arrays.asList(TreatState.RECIPIENT, TreatState.YOUR_INFO, TreatState.MESSAGE, TreatState.PROMO_CODE, TreatState.PAYMENT, TreatState.TREAT, TreatState.TREAT_COMPLETE));
        return treatPath;
    }
}
