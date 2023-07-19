package com.sharetreats01.viber_chatbot.message.support;

import com.sharetreats01.viber_chatbot.callback.enums.TreatState;
import com.sharetreats01.viber_chatbot.callback.utils.MessageUtils;
import com.sharetreats01.viber_chatbot.callback.utils.TreatMessageUtils;
import com.sharetreats01.viber_chatbot.message.dto.MessageProcessContext;
import com.sharetreats01.viber_chatbot.message.entity.ViberRichMediaButtonEntity;
import com.sharetreats01.viber_chatbot.message.entity.ViberRichMediaEntity;
import com.sharetreats01.viber_chatbot.message.enums.RichMediaType;
import com.sharetreats01.viber_chatbot.message.repository.ViberRichMediaButtonEntityRepository;
import com.sharetreats01.viber_chatbot.message.repository.ViberRichMediaEntityRepository;
import com.sharetreats01.viber_chatbot.message.util.MessagePropertyUtils;
import com.sharetreats01.viber_chatbot.viber.dto.request.MessageRequest;
import com.sharetreats01.viber_chatbot.viber.dto.request.RichMediaMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TreatMessageProducerTreatInfo implements TreatMessageProducer {
    private final TreatMessageUtils treatMessageUtils;
    private final ViberRichMediaEntityRepository richMediaEntityRepository;
    private final ViberRichMediaButtonEntityRepository richMediaButtonEntityRepository;

    @Override
    public TreatState getConstantType() {
        return TreatState.TREAT_INFO;
    }

    @Override
    public MessageRequest produceMessage(MessageProcessContext context) {
        log.info("Message Produce Treat");
        // Rich Media
        RichMediaType richMediaType = treatMessageUtils.extractRichMediaForState(context.getTrackingData());
        ViberRichMediaEntity richMedia = richMediaEntityRepository.findTopByMetaDataTypeOrderByCreatedAtDesc(richMediaType).orElseThrow();
        List<ViberRichMediaButtonEntity> buttons = null;
        if (richMediaType == RichMediaType.TREAT_FRIEND) {
            buttons = MessagePropertyUtils.ConvertToTreatFriendRichMediaButtonList(richMediaButtonEntityRepository.findAllByRichMedia_Id(richMedia.getId()), treatMessageUtils.createTreatFriendPropertyList(context.getTrackingData(), context.getInput()));
        } else if (richMediaType == RichMediaType.TREAT_ME) {
            buttons = MessagePropertyUtils.ConvertToTreatMeRichMediaButtonList(richMediaButtonEntityRepository.findAllByRichMedia_Id(richMedia.getId()), treatMessageUtils.createTreatMePropertyList(context.getTrackingData(), context.getInput()));
        }

        richMedia.setButtons(buttons);
        return new RichMediaMessageRequest(context.getReceiverId(), context.getTrackingData(), context.getMinApiVersion(), richMedia);
    }
}
