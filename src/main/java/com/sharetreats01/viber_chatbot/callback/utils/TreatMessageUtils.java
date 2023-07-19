package com.sharetreats01.viber_chatbot.callback.utils;

import com.sharetreats01.viber_chatbot.callback.dto.MessageRequestContext;
import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import com.sharetreats01.viber_chatbot.callback.enums.TreatState;
import com.sharetreats01.viber_chatbot.message.dto.TreatFriendProperty;
import com.sharetreats01.viber_chatbot.message.dto.TreatMeProperty;
import com.sharetreats01.viber_chatbot.message.enums.RichMediaButtonPropertyType;
import com.sharetreats01.viber_chatbot.message.enums.RichMediaType;
import com.sharetreats01.viber_chatbot.sharetreats.dto.ProductDetailDto;
import com.sharetreats01.viber_chatbot.sharetreats.repository.ShareTreatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TreatMessageUtils {
    private final Map<TreatState, List<TreatState>> treatPaths;
    private final ShareTreatsRepository shareTreatsRepository;
    private final String DELIMITER = "_";
    private final String TRACKING_DELIMITER = ":";
    private final String FRIEND = TreatState.FRIEND.name();
    private final String ME = TreatState.ME.name();

    public List<TreatFriendProperty> createTreatFriendPropertyList(String trackingData, String input) {
        String[] parts = trackingData.split(TRACKING_DELIMITER);
        List<String> treatParts = extractTreatParts(parts[parts.length - 1]);
        Long productId = Long.parseLong(parts[2].split(DELIMITER)[1]);
        ProductDetailDto productDetail = shareTreatsRepository.findProductDetailDtoById(productId);
        return List.of(
                TreatFriendProperty.createImage(productDetail.getProductImage()),
                TreatFriendProperty.createContent(productDetail.getProductName(), productDetail.getAmount().toString(), treatParts.get(2), treatParts.get(3), treatParts.get(4), treatParts.get(5), input),
                TreatFriendProperty.createButton()
        );
    }

    public List<TreatFriendProperty> createTreatFriendPropertyList(String trackingData) {
        String[] parts = trackingData.split(TRACKING_DELIMITER);
        List<String> treatParts = extractTreatParts(parts[parts.length - 1]);
        Long productId = Long.parseLong(parts[2].split(DELIMITER)[1]);
        ProductDetailDto productDetail = shareTreatsRepository.findProductDetailDtoById(productId);
        return List.of(
                TreatFriendProperty.createImage(productDetail.getProductImage()),
                TreatFriendProperty.createContent(productDetail.getProductName(), productDetail.getAmount().toString(), treatParts.get(2), treatParts.get(3), treatParts.get(4), treatParts.get(5), treatParts.get(6)),
                TreatFriendProperty.createButton()
        );
    }

    public List<TreatMeProperty> createTreatMePropertyList(String trackingData, String input) {
        String[] parts = trackingData.split(TRACKING_DELIMITER);
        List<String> treatParts = extractTreatParts(parts[parts.length - 1]);
        Long productId = Long.parseLong(parts[2].split(DELIMITER)[1]);
        ProductDetailDto productDetail = shareTreatsRepository.findProductDetailDtoById(productId);
        return List.of(
                TreatMeProperty.createImage(productDetail.getProductImage()),
                TreatMeProperty.createContent(productDetail.getProductName(), productDetail.getAmount().toString(), treatParts.get(2), treatParts.get(3), input),
                TreatMeProperty.createButton()
        );
    }

    public List<TreatMeProperty> createTreatMePropertyList(String trackingData) {
        String[] parts = trackingData.split(TRACKING_DELIMITER);
        List<String> treatParts = extractTreatParts(parts[parts.length - 1]);
        Long productId = Long.parseLong(parts[2].split(DELIMITER)[1]);
        ProductDetailDto productDetail = shareTreatsRepository.findProductDetailDtoById(productId);
        return List.of(
                TreatMeProperty.createImage(productDetail.getProductImage()),
                TreatMeProperty.createContent(productDetail.getProductName(), productDetail.getAmount().toString(), treatParts.get(2), treatParts.get(3), treatParts.get(4)),
                TreatMeProperty.createButton()
        );
    }
    public RichMediaType extractRichMediaForState(String trackingData) {
        if (trackingData.contains(FRIEND)) return RichMediaType.TREAT_FRIEND;
        return RichMediaType.TREAT_ME;
    }

    public RichMediaType extractRichMediaForTreatComp(String trackingData) {
        if (trackingData.contains(FRIEND)) return RichMediaType.TREAT_COMP_FRIEND;
        return RichMediaType.TREAT_COMP_ME;
    }
    public String pasteInputData(String trackingData, String input) {
        return trackingData + DELIMITER + input;
    }

    public String removeDetailState(String trackingData) {
        trackingData = trackingData.replace(":DETAIL", "");
        return trackingData + TRACKING_DELIMITER + "TREAT";
    }

    public TreatState extractHandleKey(MessageRequestContext context) {
        if (context.getInput().equals(MessageState.TREAT.name())) return TreatState.FOR;
        String[] parts = context.getTrackingData().split(TRACKING_DELIMITER);
        List<String> treatParts = extractTreatParts(parts[parts.length - 1]);

        return extractTreatHandleKey(treatParts);
    }

    public TreatState extractNextMessageKey(MessageRequestContext context) {
        if (context.getInput().contains("_TREAT")) {
            String[] parts = context.getInput().split(DELIMITER);
            context.setTrackingData(context.getTrackingData() + DELIMITER + parts[0]);
            context.setInput("TREAT");
        }

        String[] parts = context.getTrackingData().split(TRACKING_DELIMITER);
        return extractTreatMessagePathFromTreatParts(parts[parts.length - 1].startsWith("TREAT") ? extractTreatParts(parts[parts.length - 1]) : null, context.getInput());
    }

    public TreatState extractTreatMessagePathFromTreatParts(List<String> treatParts, String input) {
        if (treatParts == null) return TreatState.FOR;

        return getTreatMessageKey(treatParts, input);
    }

    private List<String> extractTreatParts(String treatPart) {
        return new ArrayList<>(Arrays.asList(treatPart.split(DELIMITER)));
    }

    public List<String> buildTrackingData(String trackingData, String input) {
        List<String> parts = new ArrayList<>(Arrays.asList(trackingData.split(TRACKING_DELIMITER)));
        if (parts.size() != 4) {
            parts.add("TREAT");
            return parts;
        }

        parts.set(parts.size() - 1, parts.get(parts.size() - 1) + DELIMITER + input);
        return parts;
    }

    public List<String> deleteLastInput(List<String> parts) {
        parts.remove(parts.size() - 1);
        return parts;
    }

    public String combinePartsToTrackingData(List<String> parts) {
        if (parts.isEmpty()) return null;
        return String.join(TRACKING_DELIMITER, parts);
    }

    public TreatState getTreatMessageKey(List<String> treatParts, String input) {
        if (treatParts.isEmpty()) return TreatState.FOR;

        TreatState pathKey;
        if (input.equals(FRIEND) || input.equals(ME)) pathKey = TreatState.fromValue(input);
        else pathKey = TreatState.fromValue(treatParts.get(1));

        List<TreatState> path = treatPaths.get(pathKey);
        int index = treatParts.size() - 1;

        if (index < path.size()) {
            return path.get(index);
        } else {
            return null;
        }
    }

    private TreatState extractTreatHandleKey(List<String> treatParts) {
        if (treatParts.isEmpty() || treatParts.size() < 2) return TreatState.FOR;

        TreatState pathKey = TreatState.fromValue(treatParts.get(1));

        List<TreatState> path = treatPaths.get(pathKey);
        int index = treatParts.size() - 2;

        if (index < path.size()) {
            return path.get(index);
        } else {
            return null;
        }
    }
}