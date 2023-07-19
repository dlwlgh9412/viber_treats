package com.sharetreats01.viber_chatbot.message.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sharetreats01.viber_chatbot.message.dto.ProductDetailButtonProperty;
import com.sharetreats01.viber_chatbot.message.dto.ProductListButtonProperty;
import com.sharetreats01.viber_chatbot.message.dto.TreatFriendProperty;
import com.sharetreats01.viber_chatbot.message.dto.TreatMeProperty;
import com.sharetreats01.viber_chatbot.message.enums.RichMediaButtonPropertyType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

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
                return createEntityOnImage(property.getImage());
            case CONTENT:
                return createEntityOnText(property.getTextValues());
            case BUTTON:
                return createEntityOnButton();
            case DETAIL:
                return createEntityOnDetail(property.getActionBody());
        }
        return this;
    }

    public ViberRichMediaButtonEntity createEntityOnProductDetailButtonProperty(ProductDetailButtonProperty property) {
        return getViberRichMediaButtonEntity(property.getType(), property.getImage(), property.getTextValues());
    }

    public ViberRichMediaButtonEntity createEntityOnTreatFriendButtonProperty(TreatFriendProperty property) {
        return getViberRichMediaButtonEntity(property.getType(), property.getProductImage(), property.getTextValues());
    }

    public ViberRichMediaButtonEntity createEntityOnTreatMeButtonProperty(TreatMeProperty property) {
        return getViberRichMediaButtonEntity(property.getType(), property.getImage(), property.getTextValues());
    }

    @NotNull
    private ViberRichMediaButtonEntity getViberRichMediaButtonEntity(RichMediaButtonPropertyType type, String image, List<String> textValues) {
        switch (type) {
            case IMAGE:
                return createEntityOnImage(image);
            case CONTENT:
                return createEntityOnText(textValues);
            case BUTTON:
                return createEntityOnButton();
        }
        return this;
    }

    private ViberRichMediaButtonEntity createEntityOnImage(String image) {
        return new ViberRichMediaButtonEntity(this.columns, this.rows, this.actionType, this.actionBody, image, this.text, this.textSize, this.textVAlign, this.textHAlign);
    }

    private ViberRichMediaButtonEntity createEntityOnText(List<String> values) {
        String text = String.format(this.text, values.toArray());
        return new ViberRichMediaButtonEntity(this.columns, this.rows, this.actionType, this.actionBody, this.image, text, this.textSize, this.textVAlign, this.textHAlign);
    }

    private ViberRichMediaButtonEntity createEntityOnButton(String actionBody) {
        return new ViberRichMediaButtonEntity(this.columns, this.rows, this.actionType, actionBody, this.image, this.text, this.textSize, this.textVAlign, this.textHAlign);
    }
    private ViberRichMediaButtonEntity createEntityOnButton() {
        return this;
    }

    private ViberRichMediaButtonEntity createEntityOnDetail(String actionBody) {
        return new ViberRichMediaButtonEntity(this.columns, this.rows, this.actionType, actionBody, this.image, this.text, this.textSize, this.textVAlign, this.textHAlign);
    }


}
