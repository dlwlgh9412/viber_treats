package com.sharetreats01.viber_chatbot.user.service;

import com.sharetreats01.viber_chatbot.common.exception.UserNotFoundException;
import com.sharetreats01.viber_chatbot.user.repository.UserRepository;
import com.sharetreats01.viber_chatbot.user.entity.UserEntity;
import com.sharetreats01.viber_chatbot.viber.client.ViberWebClient;
import com.sharetreats01.viber_chatbot.viber.dto.request.GetUserDetailsRequest;
import com.sharetreats01.viber_chatbot.viber.dto.response.GetUserDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final ViberWebClient viberWebClient;

    @Override
    @Transactional
    public void subscribe(String id) {
        UserEntity entity = repository.findById(id).orElseGet(() -> insertUser(id));
        entity.subscribe();
    }

    @Override
    @Transactional
    public void unsubscribe(String id) {
        UserEntity entity = repository.findById(id).orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));
        entity.unsubscribe();
    }

    private UserEntity insertUser(String id) {
        GetUserDetailsRequest request = new GetUserDetailsRequest(id);
        GetUserDetailsResponse response = viberWebClient.getUserDetails(request);
        UserEntity entity = new UserEntity(response.getUser().getId(), response.getUser().getName(), response.getUser().getAvatar(), response.getUser().getCountry(), response.getUser().getLanguage(), response.getUser().getApiVersion(), response.getUser().getPrimaryDeviceOs(), response.getUser().getViberVersion(), response.getUser().getDeviceType(), response.getUser().getMcc(), response.getUser().getMnc());

        return repository.save(entity);
    }
}
