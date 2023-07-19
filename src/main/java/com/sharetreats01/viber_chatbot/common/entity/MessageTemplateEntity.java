package com.sharetreats01.viber_chatbot.common.entity;

import com.sharetreats01.viber_chatbot.viber.enums.MessageType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "tb_message_template")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageTemplateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageType type;

    @Column(name = "template", nullable = false)
    private String template;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "version", nullable = false)
    private Integer version;

    @OneToMany(mappedBy = "templateEntity", fetch = FetchType.LAZY)
    private List<MessageTemplatePlaceHolderEntity> templateVariableEntities = new ArrayList<>();

    @Builder
    public MessageTemplateEntity(MessageType type, String template, String language, Integer version) {
        this.type = type;
        this.template = template;
        this.language = language;
        this.version = version;
    }
}
