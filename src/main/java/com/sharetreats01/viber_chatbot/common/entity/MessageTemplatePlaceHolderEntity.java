package com.sharetreats01.viber_chatbot.common.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_message_template_placeholder")
public class MessageTemplatePlaceHolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private MessageTemplateEntity templateEntity;

    @Column(name = "name")
    private String name;
}
