package com.sharetreats01.viber_chatbot.message.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tb_viber_rich_media_button")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ViberRichMediaButtonEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "rich_media_id")
    @ToString.Exclude
    private ViberRichMediaEntity richMedia;

    @JsonProperty("Columns")
    @Column(name = "button_columns")
    private Integer columns;

    @JsonProperty("Rows")
    @Column(name = "button_rows")
    private Integer rows;

    @JsonProperty("ActionType")
    @Column(name = "action_type")
    private String actionType;

    @JsonProperty("ActionBody")
    @Column(name = "action_body")
    private String actionBody;

    @JsonProperty("Image")
    @Column(name = "image")
    private String image;

    @JsonProperty("Text")
    @Column(name = "text")
    private String text;

    @JsonProperty("TextSize")
    @Column(name = "text_size")
    private String textSize;

    @JsonProperty("TextVAlign")
    @Column(name = "text_v_align")
    private String textVAlign;

    @JsonProperty("TextHAlign")
    @Column(name = "text_h_align")
    private String textHAlign;

    public void setRichMedia(ViberRichMediaEntity richMedia) {
        this.richMedia = richMedia;
    }

    protected ViberRichMediaButtonEntity(ViberRichMediaEntity richMedia, Integer columns, Integer rows, String actionType, String actionBody, String image, String text, String textSize, String textVAlign, String textHAlign) {
        this.richMedia = richMedia;
        this.columns = columns;
        this.rows = rows;
        this.actionType = actionType;
        this.actionBody = actionBody;
        this.image = image;
        this.text = text;
        this.textSize = textSize;
        this.textVAlign = textVAlign;
        this.textHAlign = textHAlign;
    }

    public void setText(String text) {
        this.text = String.format(this.text, text);
    }

    public void setActionBody(String actionBody) {
        this.actionBody = actionBody;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
