package com.sharetreats01.viber_chatbot.message.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sharetreats01.viber_chatbot.message.dto.ButtonPropDto;
import com.sharetreats01.viber_chatbot.message.enums.RichMediaType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @JsonProperty("ButtonsGroupColumns")
    @Column(name = "buttons_group_columns")
    private Integer buttonsGroupColumns;

    @JsonProperty("ButtonsGroupRows")
    @Column(name = "buttons_group_rows")
    private Integer buttonsGroupRows;

    @JsonProperty("BgColor")
    @Column(name = "bg_color")
    private String bgColor;

    @JsonProperty("Buttons")
    @OneToMany(mappedBy = "richMedia", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ViberRichMediaButtonEntity> buttons = new ArrayList<>();

    @JsonIgnore
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    protected ViberRichMediaEntity(String description, RichMediaType metaDataType, Integer buttonsGroupColumns, Integer buttonsGroupRows, String bgColor, LocalDateTime createdAt) {
        this.description = description;
        this.metaDataType = metaDataType;
        this.buttonsGroupColumns = buttonsGroupColumns;
        this.buttonsGroupRows = buttonsGroupRows;
        this.bgColor = bgColor;
        this.createdAt = createdAt;
    }

    public void setProductListRichMediaButtonsProp(List<ButtonPropDto> props) {
        buttons = IntStream.range(0, buttons.size())
                .filter(index -> index < props.size())
                .mapToObj(i -> {
                    ViberRichMediaButtonEntity button = buttons.get(i);
                    ButtonPropDto prop = props.get(i);
                    button.setText(prop.getText());
                    button.setActionBody(prop.getActionBody());
                    button.setImage(prop.getImage());
                    return button;
                })
                .collect(Collectors.toList());
    }
}
