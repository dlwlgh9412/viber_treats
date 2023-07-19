package com.sharetreats01.viber_chatbot.message.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sharetreats01.viber_chatbot.message.dto.ProductDetailButtonProperty;
import com.sharetreats01.viber_chatbot.message.dto.ProductListButtonProperty;
import com.sharetreats01.viber_chatbot.message.enums.RichMediaButtonPropertyType;
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

    @JsonIgnore
    @Column(name = "button_prop")
    @Enumerated(EnumType.STRING)
    private RichMediaButtonPropertyType type;

    private ViberRichMediaButtonEntity(Integer columns, Integer rows, String actionType, String actionBody, String image, String text, String textSize, String textVAlign, String textHAlign) {
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

    public ViberRichMediaButtonEntity createEntityOnProductListButtonProperty(ProductListButtonProperty property) {
        switch (property.getType()) {
            case IMAGE:
                return createEntityOnImage(property);
            case CONTENT:
                return createEntityOnText(property);
            case BUTTON:
            case DETAIL:
                return createEntityOnActionBody(property);
        }
        return null;
    }

    public ViberRichMediaButtonEntity createEntityOnProductDetailButtonProperty(ProductDetailButtonProperty property) {
        switch (property.getType()) {
            case IMAGE:
                return createEntityOnImage(property);
            case CONTENT:
                return createEntityOnText(property);
            case BUTTON:
                return createEntityOnActionBody(property);
        }
        return null;
    }

    private ViberRichMediaButtonEntity createEntityOnText(ProductListButtonProperty property) {
        String text = String.format(this.text, property.getTextValues().toArray());
        return new ViberRichMediaButtonEntity(this.columns, this.rows, this.actionType, this.actionBody, this.image, text, this.textSize, this.textVAlign, this.textHAlign);
    }

    private ViberRichMediaButtonEntity createEntityOnText(ProductDetailButtonProperty property) {
        String text = String.format(this.text, property.getTextValues().toArray());
        return new ViberRichMediaButtonEntity(this.columns, this.rows, this.actionType, this.actionBody, this.image, text, this.textSize, this.textVAlign, this.textHAlign);
    }

    private ViberRichMediaButtonEntity createEntityOnActionBody(ProductListButtonProperty property) {
        return new ViberRichMediaButtonEntity(this.columns, this.rows, this.actionType, property.getActionBody(), this.image, this.text, this.textSize, this.textVAlign, this.textHAlign);
    }

    private ViberRichMediaButtonEntity createEntityOnActionBody(ProductDetailButtonProperty property) {
        return new ViberRichMediaButtonEntity(this.columns, this.rows, this.actionType, property.getActionBody(), this.image, this.text, this.textSize, this.textVAlign, this.textHAlign);
    }

    private ViberRichMediaButtonEntity createEntityOnImage(ProductListButtonProperty property) {
        return new ViberRichMediaButtonEntity(this.columns, this.rows, this.actionType, this.actionBody, property.getImage(), this.text, this.textSize, this.textVAlign, this.textHAlign);
    }

    private ViberRichMediaButtonEntity createEntityOnImage(ProductDetailButtonProperty property) {
        return new ViberRichMediaButtonEntity(this.columns, this.rows, this.actionType, this.actionBody, property.getImage(), this.text, this.textSize, this.textVAlign, this.textHAlign);
    }
}
