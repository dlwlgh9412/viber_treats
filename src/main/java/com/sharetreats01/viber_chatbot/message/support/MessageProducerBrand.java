package com.sharetreats01.viber_chatbot.message.support;

import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import com.sharetreats01.viber_chatbot.callback.utils.MessageUtils;
import com.sharetreats01.viber_chatbot.message.dto.ButtonPropDto;
import com.sharetreats01.viber_chatbot.message.dto.MessageProcessContext;
import com.sharetreats01.viber_chatbot.message.entity.ViberKeyboardEntity;
import com.sharetreats01.viber_chatbot.message.enums.KeyboardType;
import com.sharetreats01.viber_chatbot.message.repository.ViberKeyboardEntityRepository;
import com.sharetreats01.viber_chatbot.message.util.MessagePropUtils;
import com.sharetreats01.viber_chatbot.sharetreats.dto.BrandListDto;
import com.sharetreats01.viber_chatbot.sharetreats.service.ShareTreatsService;
import com.sharetreats01.viber_chatbot.viber.dto.request.MessageRequest;
import com.sharetreats01.viber_chatbot.viber.dto.request.TextMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducerBrand implements MessageProducer {
    private final MessageUtils messageUtils;
    private final ShareTreatsService shareTreatsService;
    private final ViberKeyboardEntityRepository keyboardEntityRepository;
    @Override
    public MessageState getType() {
        return MessageState.BRANDS;
    }

    @Override
    public MessageRequest produceMessage(MessageProcessContext context) {
        log.info("MessageProduce Brand List");
        BrandListDto brandListDto = shareTreatsService.getBrandListDto();
        List<ButtonPropDto> dtoList = MessagePropUtils.ConvertToButtonsPropDtoList(brandListDto);
        ViberKeyboardEntity keyboard = keyboardEntityRepository.findTopByMetaDataTypeOrderByCreatedAtDesc(KeyboardType.BRAND).orElseThrow();
        keyboard.setBrandKeyboardButtonsProp(dtoList);
        return TextMessageRequest.createWithKeyboard(context.getReceiverId(), context.getSenderName(), context.getSenderAvatar(), context.getMinApiVersion(), keyboard, messageUtils.createTrackingData(), "브랜드를 선택해주세요.");
    }
}