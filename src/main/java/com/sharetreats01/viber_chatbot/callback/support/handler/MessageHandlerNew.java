package com.sharetreats01.viber_chatbot.callback.support.handler;

import com.sharetreats01.viber_chatbot.callback.dto.MessageRequestContext;
import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import com.sharetreats01.viber_chatbot.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageHandlerNew implements MessageHandler {
    private final UserService userService;

    @Override
    public MessageState getMessageHandleType() {
        return MessageState.NEW;
    }

    @Override
    public void handle(MessageRequestContext context) {
        log.info("Message Handle New");
        userService.subscribe(context.getReceiverId());
    }
}
