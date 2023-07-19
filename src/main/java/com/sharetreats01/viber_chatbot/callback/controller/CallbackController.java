package com.sharetreats01.viber_chatbot.callback.controller;

import com.sharetreats01.viber_chatbot.callback.dto.callback.request.CallbackDto;
import com.sharetreats01.viber_chatbot.callback.dispatch.CallbackDispatch;
import com.sharetreats01.viber_chatbot.callback.dto.callback.response.CallbackDtoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CallbackController {
    private final CallbackDispatch<CallbackDto, CallbackDtoResponse> callbackDispatch;
    @PostMapping("/sharetreats01_chatbot")
    public <T extends CallbackDto> ResponseEntity<?> callback(@RequestBody T request) {
        return ResponseEntity.ok(callbackDispatch.dispatch(request));
    }
}
