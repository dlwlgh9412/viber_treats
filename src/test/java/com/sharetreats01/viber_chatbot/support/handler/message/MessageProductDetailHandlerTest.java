package com.sharetreats01.viber_chatbot.support.handler.message;

import com.sharetreats01.viber_chatbot.AbstractMockTest;
import com.sharetreats01.viber_chatbot.dto.callback.request.MessageRequest;
import com.sharetreats01.viber_chatbot.infra.viber.client.ViberWebClient;
import com.sharetreats01.viber_chatbot.infra.viber.dto.request.SendMessageRequest;
import com.sharetreats01.viber_chatbot.support.creator.ProductDetailMessageCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageProductDetailHandlerTest extends AbstractMockTest {
    @Mock
    private ViberWebClient viberWebClient;

    @Mock
    private ProductDetailMessageCreator messageCreator;

    @InjectMocks
    private MessageProductDetailHandler messageProductDetailHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("상품 선택 시 상품 디테일 메시지 작성")
    public void handleTest() {
        MessageRequest request = JsonToValue(MessageRequest.class, "/json/SelectProductMessageRequest.json");
        SendMessageRequest messageRequest = mock(SendMessageRequest.class);

        when(messageCreator.createMessage(request)).thenReturn(messageRequest);

        messageProductDetailHandler.handle(request);

        verify(messageCreator, times(1)).createMessage(request);
        verify(viberWebClient, times(1)).sendMessage(messageRequest);
    }
}