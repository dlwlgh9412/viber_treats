package com.sharetreats01.viber_chatbot.viber.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "tb_viber_webhook_setting")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ViberWebhookSettingEntity {
    @Id
    private Integer id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "event_types", length = 100)
    private String eventTypes;

    @Column(name = "send_name", nullable = false)
    private Boolean sendName;

    @Column(name = "send_photo", nullable = false)
    private Boolean sendPhoto;

    @Column(name = "is_set", nullable = false)
    private Boolean isSet;

    @Column(name = "set_on", nullable = false)
    private LocalDateTime setOn;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public ViberWebhookSettingEntity(String url, String eventTypes, Boolean sendName, Boolean sendPhoto, Boolean isSet, LocalDateTime setOn, LocalDateTime createdAt) {
        this.url = url;
        this.eventTypes = eventTypes;
        this.sendName = sendName;
        this.sendPhoto = sendPhoto;
        this.isSet = isSet;
        this.setOn = setOn;
        this.createdAt = createdAt;
    }

    public void setComplete() {
        this.setOn = LocalDateTime.now();
        this.isSet = true;
    }

    @Override
    public String toString() {
        return "ViberWebhookSettingEntity{" +
                "url='" + url + '\'' +
                ", eventTypes='" + eventTypes + '\'' +
                ", sendName=" + sendName +
                ", sendPhoto=" + sendPhoto +
                ", isSet=" + isSet +
                ", setOn=" + setOn +
                ", createdAt=" + createdAt +
                '}';
    }
}
