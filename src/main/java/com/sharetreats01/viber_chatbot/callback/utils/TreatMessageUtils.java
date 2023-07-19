package com.sharetreats01.viber_chatbot.callback.utils;

import com.sharetreats01.viber_chatbot.callback.dto.MessageRequestContext;
import com.sharetreats01.viber_chatbot.callback.enums.MessageState;
import com.sharetreats01.viber_chatbot.callback.enums.TreatState;
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
    private final String DELIMITER = "_";
    private final String TRACKING_DELIMITER = ":";
    private final String FRIEND = TreatState.FRIEND.name();
    private final String ME = TreatState.ME.name();

    public String pasteInputData(String trackingData, String input) {
        return trackingData + DELIMITER + input;
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