package com.sharetreats01.viber_chatbot.callback.service;

import com.sharetreats01.viber_chatbot.AbstractMockTest;
import com.sharetreats01.viber_chatbot.callback.dto.callback.request.CallbackDto;
import com.sharetreats01.viber_chatbot.callback.dto.callback.request.CallbackDtoMessage;
import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import com.sharetreats01.viber_chatbot.callback.support.handler.MessageHandler;
import com.sharetreats01.viber_chatbot.callback.support.handler.MessageHandlerNew;
import com.sharetreats01.viber_chatbot.callback.support.handler.MessageHandlerTreat;
import com.sharetreats01.viber_chatbot.callback.utils.MessageUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Map;

import static org.mockito.Mockito.mock;

class CallbackServiceMessageMockTest extends AbstractMockTest {
    @Mock
    Map<MessageState, MessageHandler> handlers;
    @Mock
    MessageUtils messageUtils;

    @InjectMocks
    private CallbackServiceMessage callbackServiceMessage;

    @BeforeEach
    void setUp() {
        handlers = Map.of(
                MessageState.NEW, mock(MessageHandlerNew.class),
                MessageState.TREAT, mock(MessageHandlerTreat.class)
        );
    }

    @Test
    @DisplayName("사용자가 구독이나 첫메시지를 보낼 때 트래킹데이터에 세션과 브랜드 플래그 삽입하여 반환")
    void newMessage() {
        CallbackDtoMessage callbackDtoMessage = JsonToValue(CallbackDtoMessage.class, "/json/NewMessageRequest.json");
        callbackServiceMessage.handleEvent(callbackDtoMessage);
    }

}