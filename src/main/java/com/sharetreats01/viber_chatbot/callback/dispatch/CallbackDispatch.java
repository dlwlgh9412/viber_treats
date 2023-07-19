package com.sharetreats01.viber_chatbot.callback.dispatch;

import com.sharetreats01.viber_chatbot.callback.dto.callback.request.CallbackDto;
import com.sharetreats01.viber_chatbot.callback.dto.callback.response.CallbackDtoResponse;
import com.sharetreats01.viber_chatbot.callback.service.CallbackService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CallbackDispatch<T extends CallbackDto, R extends CallbackDtoResponse> {
    private final Map<Class<T>, CallbackService<T, R>> serviceMap;

    public CallbackDispatch(final List<CallbackService<T, R>> serviceList) {
        this.serviceMap = serviceList.stream()
                .collect(Collectors.toMap(CallbackService::getCallbackType, Function.identity()));
    }

    public R dispatch(T data) {
        return serviceMap.get(data.getClass()).handleEvent(data);
    }
}
