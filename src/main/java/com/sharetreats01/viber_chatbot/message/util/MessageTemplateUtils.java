package com.sharetreats01.viber_chatbot.message.util;

import com.sharetreats01.viber_chatbot.common.exception.MessageTemplateException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageTemplateUtils {
    private static final Pattern PATTERN = Pattern.compile("\\{(\\w+)}");

    public static String processTemplate(String template, final Map<String, String> values) {
        StringBuilder result = new StringBuilder();

        Matcher matcher = PATTERN.matcher(template);

        while (matcher.find()) {
            String key = matcher.group(1);
            String value = values.get(key);

            if (value == null) {
                throw new IllegalStateException("No value provided for key: " + key);
            }

            matcher.appendReplacement(result, value);
        }

        matcher.appendTail(result);

        return result.toString();
    }

    public static Map<String, String> createTemplateValues(List<String> placeHolders, List<String> values) {
        if (placeHolders.size() != values.size()) {
            throw new MessageTemplateException("메시지 템플릿 자리 표시자의 개수와 메시지 템플릿 변수의 개수가 일치하지 않습니다.");
        }
        Map<String, String> result = new HashMap<>();
        for(int i = 0; i < placeHolders.size(); i++) {
            result.put(placeHolders.get(i), values.get(i));
        }

        return result;
    }
}
