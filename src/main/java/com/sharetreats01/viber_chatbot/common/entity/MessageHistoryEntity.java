package com.sharetreats01.viber_chatbot.common.entity;

import com.sharetreats01.viber_chatbot.common.enums.MessageDirection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tb_message_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageHistoryEntity {
    @Id
    @Column(name = "session_id", columnDefinition = "BINARY(16)")
    private UUID sessionId;

    @Column(name = "type", length = 10, nullable = false)
    private String type;

    @Column(name = "user_id", length = 255, nullable = false)
    private String userId;

    @Column(name = "content", nullable = false)
    private String content;

    @Enumerated
    @Column(name = "direction", length = 10, nullable = false)
    private MessageDirection direction;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timeStamp;
}
