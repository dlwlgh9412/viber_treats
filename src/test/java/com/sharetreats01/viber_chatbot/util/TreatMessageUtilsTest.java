package com.sharetreats01.viber_chatbot.util;

import com.sharetreats01.viber_chatbot.AbstractMockTest;
import com.sharetreats01.viber_chatbot.callback.config.MessageHandlerConfiguration;
import com.sharetreats01.viber_chatbot.callback.dto.callback.request.CallbackDtoMessage;
import com.sharetreats01.viber_chatbot.callback.utils.TreatMessageUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {TreatMessageUtils.class, MessageHandlerConfiguration.class})
class TreatMessageUtilsTest extends AbstractMockTest {
    @Autowired
    private TreatMessageUtils treatMessageUtils;

    @Test
    @DisplayName("사용자가 Treat 버튼을 눌렀을 때 TrackingData의 List size는 4이고, treatPart는 TREAT 문자열로 시작")
    public void buildTrackingDataTest() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/TreatProductMessageRequest.json");
        String trackingData  = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        List<String> result = treatMessageUtils.buildTrackingData(trackingData, input);

        assertEquals(4, result.size());
        assertEquals("TREAT", result.get(result.size() - 1));
    }

    @Test
    @DisplayName("사용자가 Treat 버튼을 누르고 Treat 대상자 ME를 입력했을 때 List Size는 4이고, TreatPart의 값은 TREAT(DELIMITER)ME")
    public void buildTrackingDataTestFor() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/TreatMeMessageRequest.json");
        String trackingData  = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        List<String> result = treatMessageUtils.buildTrackingData(trackingData, input);

        assertEquals(4, result.size());
        assertEquals("TREAT ME", result.get(result.size() - 1));
    }
    @Test
    @DisplayName("사용자에게 상품상세 메시지 전송 후 사용자로부터 TREAT 메시지를 받을 경우 TREAT 문자열 하나만 담긴 리스트를 반환")
    public void combineTreatDataTestByTreatProduct() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/TreatProductMessageRequest.json");
        String trackingData  = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        List<String> result = treatMessageUtils.buildTrackingData(trackingData, input);

        assertEquals(1, result.size());
        assertEquals("TREAT", result.get(0));
    }

    @Test
    @DisplayName("사용자가 TREAT 대상을 ME로 입력")
    public void combineTreatDataTestByTreatTargetMe() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/TreatMeMessageRequest.json");
        String trackingData  = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        List<String> result = treatMessageUtils.buildTrackingData(trackingData, input);

        assertEquals(2, result.size());
        assertEquals("ME", result.get(1));
    }

    @Test
    @DisplayName("사용자가 TREAT 대상을 ME로 입력 후 tracking_data로 변환")
    public void treatPartsToStringTestByTreatTargetMe() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/TreatMeMessageRequest.json");
        String trackingData  = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        List<String> treatParts = treatMessageUtils.buildTrackingData(trackingData, input);

        String result = treatMessageUtils.combinePartsToTrackingData(treatParts);

        assertEquals("TREAT ME", result);
    }

    @Test
    @DisplayName("사용자가 TREAT 대상을 FRIEND로 입력")
    public void combineTreatDataTestByTreatTargetFriend() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/TreatFriendMessageRequest.json");
        String trackingData  = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        List<String> result = treatMessageUtils.buildTrackingData(trackingData, input);

        assertEquals(2, result.size());
        assertEquals("FRIEND", result.get(1));
    }

    @Test
    @DisplayName("사용자가 TREAT 대상을 FRIEND로 입력 후 tracking_data로 변환")
    public void treatPartsToStringTestByTreatTargetFriend() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/TreatFriendMessageRequest.json");
        String trackingData  = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        List<String> treatParts = treatMessageUtils.buildTrackingData(trackingData, input);

        String result = treatMessageUtils.combinePartsToTrackingData(treatParts);

        assertEquals("TREAT FRIEND", result);
    }

    @Test
    @DisplayName("사용자가 TREAT 대상을 FRIEND로 설정하여 RECIPIENT 입력")
    public void combineTreatDataTestByFriendRecipient() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/TreatFriendRecipientMessageRequest.json");
        String trackingData  = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        List<String> result = treatMessageUtils.buildTrackingData(trackingData, input);

        assertEquals(3, result.size());
        assertEquals("FRIEND", result.get(1));
        assertEquals("이름/010-0000-0000/이메일@주소", result.get(2));
    }

    @Test
    @DisplayName("사용자가 TREAT 대상을 FRIEND로 설정하여 RECIPIENT 입력 후 tracking_data로 변환")
    public void treatPartsToStringTestByTreatFriendRecipient() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/TreatFriendRecipientMessageRequest.json");
        String trackingData  = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        List<String> treatParts = treatMessageUtils.buildTrackingData(trackingData, input);

        String result = treatMessageUtils.combinePartsToTrackingData(treatParts);

        assertEquals("TREAT FRIEND 이름/010-0000-0000/이메일@주소", result);
    }

    @Test
    @DisplayName("사용자가 TREAT 대상을 ME로 설정하여 YOUR_INFO 입력")
    public void combineTreatDataTestByMeYourInfo() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/TreatMeYourInfoMessageRequest.json");
        String trackingData  = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        List<String> result = treatMessageUtils.buildTrackingData(trackingData, input);

        assertEquals(3, result.size());
        assertEquals("ME", result.get(1));
        assertEquals("이름/010-0000-0000/이메일@주소", result.get(2));
    }

    @Test
    @DisplayName("사용자가 TREAT 대상을 ME로 설정하여 YOUR_INFO 입력 후 해당 프로세스가 실패하여 YOUR_INFO 삭제")
    public void deleteLastInputTestByMeYouInfo() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/TreatMeYourInfoMessageRequest.json");
        String trackingData  = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        List<String> treatParts = treatMessageUtils.buildTrackingData(trackingData, input);
        List<String> result = treatMessageUtils.deleteLastInput(treatParts);

        assertEquals(2, result.size());
        assertEquals("ME", result.get(1));
    }

    @Test
    @DisplayName("사용자가 TREAT 대상을 ME로 설정하여 YOUR_INFO 입력 후 tracking_data로 변환")
    public void treatPartsToStringTestByTreatMeYourInfo() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/TreatMeYourInfoMessageRequest.json");
        String trackingData  = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        List<String> treatParts = treatMessageUtils.buildTrackingData(trackingData, input);

        String result = treatMessageUtils.combinePartsToTrackingData(treatParts);

        assertEquals("TREAT ME 이름/010-0000-0000/이메일@주소", result);
    }

    @Test
    @DisplayName("사용자가 TREAT 대상을 FRIEND로 설정하여 YOUR_INFO 입력")
    public void combineTreatDataTestByTreatFriendYourInfo() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/TreatFriendYourInfoMessageRequest.json");
        String trackingData  = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        List<String> result = treatMessageUtils.buildTrackingData(trackingData, input);

        assertEquals(4, result.size());
        assertEquals("FRIEND", result.get(1));
        assertEquals("이름/010-0000-0000/이메일@주소", result.get(2));
        assertEquals("이름/010-0000-0000/이메일@주소", result.get(3));
    }

    @Test
    @DisplayName("사용자가 TREAT 대상을 FRIEND로 설정하여 YOUR_INFO 입력 후 tracking_data로 변환")
    public void treatPartsToStringTestByTreatFriendYourInfo() {
        CallbackDtoMessage request = JsonToValue(CallbackDtoMessage.class, "/json/TreatFriendYourInfoMessageRequest.json");
        String trackingData  = request.getMessage().getTrackingData();
        String input = request.getMessage().getText();

        List<String> treatParts = treatMessageUtils.buildTrackingData(trackingData, input);
        String result = treatMessageUtils.combinePartsToTrackingData(treatParts);

        assertEquals("TREAT FRIEND 이름/010-0000-0000/이메일@주소 이름/010-0000-0000/이메일@주소", result);
    }
}