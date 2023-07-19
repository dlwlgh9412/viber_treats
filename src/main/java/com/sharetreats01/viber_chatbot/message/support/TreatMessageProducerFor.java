package com.sharetreats01.viber_chatbot.message.support;

import com.sharetreats01.viber_chatbot.callback.enums.TreatState;
import com.sharetreats01.viber_chatbot.message.dto.MessageProcessContext;
import com.sharetreats01.viber_chatbot.message.entity.ViberKeyboardEntity;
import com.sharetreats01.viber_chatbot.message.enums.KeyboardType;
import com.sharetreats01.viber_chatbot.message.repository.ViberKeyboardEntityRepository;
import com.sharetreats01.viber_chatbot.viber.dto.request.MessageRequest;
import com.sharetreats01.viber_chatbot.viber.dto.request.TextMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class TreatMessageProducerFor implements TreatMessageProducer {
    private final ViberKeyboardEntityRepository keyboardEntityRepository;
    @Override
    public TreatState getConstantType() {
        return TreatState.FOR;
    }

    @Override
    public MessageRequest produceMessage(MessageProcessContext context) {
        log.info("Message Produce Treat For");
        ViberKeyboardEntity keyboard = keyboardEntityRepository.findTopByMetaDataTypeOrderByCreatedAtDesc(KeyboardType.FOR).orElseThrow();
        return TextMessageRequest.createWithKeyboard(context.getReceiverId(), context.getSenderName(), context.getSenderAvatar(), context.getMinApiVersion(), keyboard, context.getTrackingData(), "Treat 대상을 선택해주세요.");
    }
}
