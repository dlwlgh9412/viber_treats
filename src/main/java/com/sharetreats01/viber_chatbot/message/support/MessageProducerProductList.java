package com.sharetreats01.viber_chatbot.message.support;

import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import com.sharetreats01.viber_chatbot.callback.utils.MessageUtils;
import com.sharetreats01.viber_chatbot.message.dto.MessageProcessContext;
import com.sharetreats01.viber_chatbot.message.repository.ViberRichMediaEntityRepository;
import com.sharetreats01.viber_chatbot.sharetreats.dto.ProductListDto;
import com.sharetreats01.viber_chatbot.sharetreats.service.ShareTreatsService;
import com.sharetreats01.viber_chatbot.viber.dto.request.MessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducerProductList implements MessageProducer {
    private final MessageUtils messageUtils;
    private final ShareTreatsService shareTreatsService;
    private final ViberRichMediaEntityRepository richMediaEntityRepository;
    @Override
    public MessageState getType() {
        return MessageState.PRODUCTS;
    }

    @Override
    public MessageRequest produceMessage(MessageProcessContext context) {
        log.info("Message Produce Product List");
        ProductListDto productListDto = shareTreatsService.getProductListDtoByBrandId(context.getInput());
        return null;

    }
}
