package com.sharetreats01.viber_chatbot.message.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sharetreats01.viber_chatbot.message.dto.ButtonPropDto;
import com.sharetreats01.viber_chatbot.message.enums.KeyboardType;
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
@Table(name = "tb_viber_keyboard")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ViberKeyboardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonIgnore
    @Column(name = "metadata_type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private KeyboardType metaDataType;

    @Column(name = "description", nullable = false, length = 50)
    @JsonIgnore
    private String description;

    @Column(name = "created_at")
    @JsonIgnore
    private LocalDateTime createdAt;

    @JsonProperty("BgColor")
    @Column(name = "bg_color", length = 10)
    private String bgColor;

    @JsonProperty("DefaultHeight")
    @Column(name = "default_height")
    private Boolean defaultHeight;

    @JsonProperty("CustomDefaultHeight")
    @Column(name = "custom_default_height")
    private Integer customDefaultHeight;

    @JsonProperty("HeightScale")
    @Column(name = "height_scale")
    private Integer heightScale;

    @JsonProperty("ButtonsGroupColumns")
    @Column(name = "buttons_group_columns")
    private Integer buttonsGroupColumns;

    @JsonProperty("ButtonsGroupRows")
    @Column(name = "buttons_group_rows")
    private Integer buttonsGroupRows;

    @JsonProperty("InputFieldState")
    @Column(name = "input_field_state", length = 10)
    private String inputFieldState;

    @JsonProperty("Buttons")
    @OneToMany(mappedBy = "keyboard", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ViberKeyboardButtonEntity> buttons = new ArrayList<>();

    public void setBrandKeyboardButtonsProp(List<ButtonPropDto> props) {
        buttons = IntStream.range(0, buttons.size())
                .filter(index -> index < props.size())
                .mapToObj(i -> {
                    ViberKeyboardButtonEntity button = buttons.get(i);
                    ButtonPropDto prop = props.get(i);
                    button.setText(prop.getTextValues());
                    button.setActionBody(prop.getActionBody());
                    button.setImage(prop.getImage());
                    return button;
                })
                .collect(Collectors.toList());
    }
}
