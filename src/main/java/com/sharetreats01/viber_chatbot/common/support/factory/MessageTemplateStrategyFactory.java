package com.sharetreats01.viber_chatbot.common.support.factory;

import com.sharetreats01.viber_chatbot.callback.dto.message.template.MessageTemplateValueDto;
import com.sharetreats01.viber_chatbot.common.exception.MessageTemplateException;
import com.sharetreats01.viber_chatbot.common.support.strategy.MessageTemplateStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageTemplateStrategyFactory {
    private final Map<Class<? extends MessageTemplateValueDto>, MessageTemplateStrategy<? extends MessageTemplateValueDto>> strategies = new HashMap<>();

    public <T extends MessageTemplateValueDto> void registerStrategy(Class<T> dtoClass, MessageTemplateStrategy<T> strategy) {
        strategies.put(dtoClass, strategy);
    }

    @SuppressWarnings("unchecked")
    public <T extends MessageTemplateValueDto> MessageTemplateStrategy<T> getInstance(T dto) {
        MessageTemplateStrategy<T> strategy = (MessageTemplateStrategy<T>) strategies.get(dto.getClass());
        if (strategy == null)
            throw new MessageTemplateException("메시지 템플릿 생성 인스턴스를 찾을 수 없습니다.");
        return strategy;
    }
}
