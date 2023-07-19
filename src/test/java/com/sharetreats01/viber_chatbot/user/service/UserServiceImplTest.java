package com.sharetreats01.viber_chatbot.user.service;

import com.sharetreats01.viber_chatbot.user.entity.UserEntity;
import com.sharetreats01.viber_chatbot.user.repository.UserRepository;
import com.sharetreats01.viber_chatbot.viber.client.ViberWebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ViberWebClient viberWebClient;

    @InjectMocks
    private UserServiceImpl userService;

    private UserEntity userEntity;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.userEntity = new UserEntity("01234567890A=", "John McClane", "http://avatar.example.com", "UK", "en", 1, "android 7.1", "6.5.0", "iPhone9,4", 1, 1);
    }

//    @Test
//    public void 유저_구독_테스트() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        User user = UserFactory.createUserDto("01234567890A=", "John McClane", "http://avatar.example.com", "UK", "en", 1);
//        GetUserDetailsResponse response = GetUserDetailsResponseFactory.create();
//        when(userRepository.findById(any(String.class))).thenReturn(Optional.empty());
//        when(viberWebClient.getUserDetails(any(GetUserDetailsRequest.class))).thenReturn(response);
//        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
//
//        userService.subscribe(user.getId());
//
//        verify(userRepository, times(1)).findById(any(String.class));
//        verify(viberWebClient, times(1)).getUserDetails(any(GetUserDetailsRequest.class));
//        verify(userRepository, times(1)).save(any(UserEntity.class));
//
//        assertTrue(userEntity.getIsSubscribe());
//    }
}