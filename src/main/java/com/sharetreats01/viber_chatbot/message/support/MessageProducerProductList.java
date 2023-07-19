package com.sharetreats01.viber_chatbot.message.support;

import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import com.sharetreats01.viber_chatbot.callback.utils.MessageUtils;
import com.sharetreats01.viber_chatbot.message.dto.MessageProcessContext;
import com.sharetreats01.viber_chatbot.message.entity.ViberRichMediaButtonEntity;
import com.sharetreats01.viber_chatbot.message.entity.ViberRichMediaEntity;
import com.sharetreats01.viber_chatbot.message.enums.RichMediaType;
import com.sharetreats01.viber_chatbot.message.repository.ViberRichMediaButtonEntityRepository;
import com.sharetreats01.viber_chatbot.message.repository.ViberRichMediaEntityRepository;
import com.sharetreats01.viber_chatbot.message.util.MessagePropertyUtils;
import com.sharetreats01.viber_chatbot.sharetreats.dto.ProductListDto;
import com.sharetreats01.viber_chatbot.sharetreats.service.ShareTreatsService;
import com.sharetreats01.viber_chatbot.viber.dto.request.MessageRequest;
import com.sharetreats01.viber_chatbot.viber.dto.request.RichMediaMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducerProductList implements MessageProducer {
    private final MessageUtils messageUtils;
    private final ShareTreatsService shareTreatsService;
    private final ViberRichMediaEntityRepository richMediaEntityRepository;
    private final ViberRichMediaButtonEntityRepository richMediaButtonEntityRepository;

    @Override
    public MessageState getType() {
        return MessageState.PRODUCTS;
    }

    @Override
    public MessageRequest produceMessage(MessageProcessContext context) {
        log.info("Message Produce Product List");
        ProductListDto productListDto = shareTreatsService.getProductListDtoByBrandId(Long.parseLong(context.getInput()));
        ViberRichMediaEntity richMedia = richMediaEntityRepository.findTopByMetaDataTypeOrderByCreatedAtDesc(RichMediaType.PRODUCT_LIST).orElseThrow();
        List<ViberRichMediaButtonEntity> buttons = MessagePropertyUtils.ConvertToProductsRichMediaButtonList(richMediaButtonEntityRepository.findAllByRichMedia_Id(richMedia.getId()), productListDto);
        richMedia.setButtons(buttons);

        return new RichMediaMessageRequest(context.getReceiverId(), messageUtils.createReplyTrackingData(context.getTrackingData(), getType().name(), context.getInput()), context.getMinApiVersion(), richMedia);
    }
}
