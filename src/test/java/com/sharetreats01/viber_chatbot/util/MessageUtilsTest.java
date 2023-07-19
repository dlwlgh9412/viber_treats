package com.sharetreats01.viber_chatbot.util;

import com.sharetreats01.viber_chatbot.AbstractMockTest;
import com.sharetreats01.viber_chatbot.callback.config.MessageHandlerConfiguration;
import com.sharetreats01.viber_chatbot.callback.dto.callback.request.CallbackDtoMessage;
import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import com.sharetreats01.viber_chatbot.callback.utils.MessageUtils;
import com.sharetreats01.viber_chatbot.callback.utils.UUIDGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MessageUtils.class, UUIDGenerator.class, MessageHandlerConfiguration.class})
class MessageUtilsTest extends AbstractMockTest {
    @Autowired
    private MessageUtils messageUtils;

    private final String session = "11ee1982-1bef-3bc7-8664-cf1e0bd45cbf";

    @Test
    @DisplayName("사용자에게 구독 메시지 또는 첫 메시지를 받으면 NEW 반환")
    void extractHandleKeyTest_NEW() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/FirstMessageRequest.json");
        String tackingData = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        MessageState result = messageUtils.extractHandleKey(tackingData, input);

        assertEquals(MessageState.NEW, result);
    }

    @Test
    @DisplayName("사용자가 브랜드를 선택 한 경우 BRAND 반환")
    void extractHandleKeyTest_BRAND() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/SelectBrandMessageRequest.json");
        String tackingData = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        MessageState result = messageUtils.extractHandleKey(tackingData, input);

        assertEquals(MessageState.PRODUCTS, result);
    }

    @Test
    @DisplayName("사용자가 PRODUCTS에서 Send Treat를 선택한 경우 Treat반환")
    void extractHandleKeyTest_TREAT() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/SelectSendTreatAtProductsRequest.json");
        String tackingData = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        MessageState result = messageUtils.extractHandleKey(tackingData, input);

        assertEquals(MessageState.TREAT, result);
    }

    @Test
    @DisplayName("사용자가 상품을 선택 한 경우 DETAIL 반환")
    void getNextStateByProductMessage() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/SelectProductMessageRequest.json");
        String tackingData = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        MessageState result = messageUtils.extractHandleKey(tackingData, input);

        assertEquals(MessageState.DETAIL, result);
    }

    @Test
    @DisplayName("사용자가 상품 상세정보 후 Treat 선택 한 경우")
    void getNextStateByDetailMessage() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/SelectTreatMessageRequest.json");
        String tackingData = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        MessageState result = messageUtils.extractHandleKey(tackingData, input);

        assertEquals(MessageState.TREAT, result);
    }

    @Test
    @DisplayName("사용자가 브랜드를 선택하면 해당 상품 리스트를 반환하기 위해 PRODUCTS 상태값을 반환")
    void extractStateForMessageTest_PRODUCTS() {

    }
}