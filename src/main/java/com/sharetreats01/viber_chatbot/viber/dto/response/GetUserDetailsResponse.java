package com.sharetreats01.viber_chatbot.viber.dto.response;

import com.sharetreats01.viber_chatbot.viber.enums.Status;
import com.sharetreats01.viber_chatbot.user.dto.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetUserDetailsResponse {
    private Status status;
    private String statusMessage;
    private Long messageToken;
    private User user;

    @Override
    public String toString() {
        return "GetUserDetailsResponse{" +
                "status=" + status +
                ", statusMessage='" + statusMessage + '\'' +
                ", messageToken=" + messageToken +
                ", user=" + user +
                '}';
    }
}
