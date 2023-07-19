package com.sharetreats01.viber_chatbot.common.service;

import com.sharetreats01.viber_chatbot.callback.dto.message.template.TemplateDto;
import com.sharetreats01.viber_chatbot.callback.dto.message.template.MessageTemplateValueDto;
import com.sharetreats01.viber_chatbot.common.entity.MessageTemplateEntity;
import com.sharetreats01.viber_chatbot.common.entity.MessageTemplatePlaceHolderEntity;
import com.sharetreats01.viber_chatbot.callback.enums.TreatState;
import com.sharetreats01.viber_chatbot.common.exception.MessageTemplateException;
import com.sharetreats01.viber_chatbot.common.repository.MessageTemplateRepository;
import com.sharetreats01.viber_chatbot.common.repository.TreatMessageRepository;
import com.sharetreats01.viber_chatbot.common.support.factory.MessageTemplateStrategyFactory;
import com.sharetreats01.viber_chatbot.common.support.strategy.MessageTemplateStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageService {
    private final MessageTemplateStrategyFactory templateStrategyFactory;
    private final MessageTemplateRepository messageTemplateRepository;
    private final TreatMessageRepository treatMessageRepository;


    public String findTreatSuccessMessage(TreatState constant) {
        return treatMessageRepository.findSuccessFirstByConstantAndVersion(constant).orElseThrow();
    }

    public String findTreatFailureMessage(TreatState constant) {
        return treatMessageRepository.findFailureFirstByConstantAndVersion(constant).orElseThrow();
    }

    public String createMessage(MessageTemplateValueDto valueDto) {
        MessageTemplateStrategy<MessageTemplateValueDto> strategy = templateStrategyFactory.getInstance(valueDto);
        MessageTemplateEntity entity =
                messageTemplateRepository
                        .findTopByTypeAndLanguageOrderByVersionDesc(valueDto.getType(), valueDto.getLanguage())
                        .orElseThrow(() -> new MessageTemplateException("메시지 템플릿을 찾을 수 없습니다."));
        TemplateDto templateDto =
                new TemplateDto(entity.getTemplate(), entity.getTemplateVariableEntities()
                        .stream()
                        .map(MessageTemplatePlaceHolderEntity::getName)
                        .collect(Collectors.toList())
                );
        return strategy.create(templateDto, valueDto);
    }

    public String findBrandsMessage() {
        return "Select Brand";
    }
}
