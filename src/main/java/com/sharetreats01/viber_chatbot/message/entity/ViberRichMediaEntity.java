package com.sharetreats01.viber_chatbot.message.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sharetreats01.viber_chatbot.message.enums.RichMediaType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "tb_viber_rich_media")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ViberRichMediaEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @JsonIgnore
    @Column(name = "metadata_type", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private RichMediaType metaDataType;

    @JsonProperty("Type")
    @Column(name = "rich_media_type", length = 10, nullable = false)
    private String type;

    @JsonProperty("ButtonsGroupColumns")
    @Column(name = "buttons_group_columns")
    private Integer buttonsGroupColumns;

    @JsonProperty("ButtonsGroupRows")
    @Column(name = "buttons_group_rows")
    private Integer buttonsGroupRows;

    @JsonProperty("BgColor")
    @Column(name = "bg_color")
    private String bgColor;

    @Transient
    @JsonProperty("Buttons")
    private List<ViberRichMediaButtonEntity> buttons;

    @JsonIgnore
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public void setButtons(List<ViberRichMediaButtonEntity> buttons) {
        this.buttons = buttons;
    }
}
