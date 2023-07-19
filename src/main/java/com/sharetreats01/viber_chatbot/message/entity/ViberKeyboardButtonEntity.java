package com.sharetreats01.viber_chatbot.message.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "tb_viber_keyboard_button")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ViberKeyboardButtonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "keyboard_id")
    @ToString.Exclude
    private ViberKeyboardEntity keyboard;

    @Column(name = "button_columns")
    @JsonProperty("Columns")
    private Integer columns;

    @Column(name = "button_rows")
    @JsonProperty("Rows")
    private Integer rows;

    @Column(name = "text")
    @JsonProperty("Text")
    private String text;

    @Column(name = "text_h_align")
    @JsonProperty("TextHAlign")
    private String textHAlign;

    @Column(name = "text_v_align")
    @JsonProperty("TextVAlign")
    private String textVAlign;

    @Column(name = "action_type")
    @JsonProperty("ActionType")
    private String actionType;

    @Column(name = "action_body")
    @JsonProperty("ActionBody")
    private String actionBody;

    @Column(name = "bg_color")
    @JsonProperty("BgColor")
    private String bgColor;

    @Column(name = "image")
    @JsonProperty("Image")
    private String image;

    public void setText(List<String> values) {
        this.text = String.format(this.text, values.toArray());
    }

    public void setActionBody(String actionBody) {
        this.actionBody = actionBody;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
