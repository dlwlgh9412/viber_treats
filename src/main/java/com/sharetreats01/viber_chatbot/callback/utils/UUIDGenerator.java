package com.sharetreats01.viber_chatbot.callback.utils;

import com.fasterxml.uuid.Generators;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDGenerator {
    public UUID createTimeBasedUUID() {
        UUID uuid = Generators.timeBasedGenerator().generate();
        String[] uuidArr = uuid.toString().split("-");
        String uuidStr = uuidArr[2] + uuidArr[1] + uuidArr[0] + uuidArr[3] + uuidArr[4];
        StringBuilder sb = new StringBuilder(uuidStr);
        sb.insert(8, "-");
        sb.insert(13, "-");
        sb.insert(18, "-");
        sb.insert(23, "-");
        uuid = UUID.fromString(sb.toString());

        return uuid;
    }
}
