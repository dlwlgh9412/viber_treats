package com.sharetreats01.viber_chatbot.interaction.handler;

import com.sharetreats01.viber_chatbot.interaction.properties.InteractionProperties;
import com.sharetreats01.viber_chatbot.interaction.service.MessageService;
import com.sharetreats01.viber_chatbot.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.openMocks;

class ConversationStartedEventHandlerTest {
    @Mock
    private InteractionProperties interactionProperties;

    @Mock
    private MessageService messageService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ConversationStartedEventHandler conversationStartedEventHandler;


    @BeforeEach
    public void init() {
        openMocks(this);
    }
    @Test
    public void testEventHandle() {
    }

}