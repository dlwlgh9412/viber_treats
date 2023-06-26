package com.sharetreats01.viber_chatbot.viber.service;

import com.sharetreats01.viber_chatbot.enums.Status;
import com.sharetreats01.viber_chatbot.utils.RetrofitUtils;
import com.sharetreats01.viber_chatbot.viber.client.ViberRetrofitClient;
import com.sharetreats01.viber_chatbot.viber.dto.SetWebhookRequest;
import com.sharetreats01.viber_chatbot.viber.dto.SetWebhookResponse;
import com.sharetreats01.viber_chatbot.viber.entity.ViberWebhookSettingEntity;
import com.sharetreats01.viber_chatbot.viber.repository.ViberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.ReflectionUtils;
import retrofit2.Call;
import retrofit2.mock.Calls;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ViberServiceTest {
    @Mock
    private ViberRepository viberRepository;

    @Mock
    private ViberRetrofitClient viberRetrofitClient;

    @InjectMocks
    private ViberService viberService;

    private ViberWebhookSettingEntity mockEntity;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockEntity = new ViberWebhookSettingEntity("http://localhost", "conversation_started, delivered", true, true, false, null, LocalDateTime.now());
    }

    @Test
    public void 이벤트타입_문자열_분리() {
        Method method = ReflectionUtils.findMethod(ViberService.class, "splitEventTypes", String.class);
        assert method != null;
        ReflectionUtils.makeAccessible(method);
        List<String> result = (List<String>) ReflectionUtils.invokeMethod(method, viberService, "conversation_started");
        assert result != null;
        assertEquals(1, result.size());
    }

    @Test
    public void 웹훅_설정() throws IOException {
//        SetWebhookRequest request = new SetWebhookRequest("https://viber.com", List.of("conversation_started"), true, true);
//        SetWebhookResponse response = new SetWebhookResponse(Status.OK, "statusMessage", List.of("conversation_started"));
//        Call<SetWebhookResponse> call = Calls.response(response);
//        when(viberRepository.findRecentWebhookSetting()).thenReturn(mockEntity);
//        when(viberRetrofitClient.sendWebhookSetting(request)).thenReturn(call);
//        when(RetrofitUtils.execute(call)).thenReturn(response);

//        viberService.setViberWebhook();

//        verify(viberRepository, times(1)).findRecentWebhookSetting();
//        verify(viberRetrofitClient, times(1)).sendWebhookSetting(request);

//        assertTrue(mockEntity.getIsSet());
    }
}