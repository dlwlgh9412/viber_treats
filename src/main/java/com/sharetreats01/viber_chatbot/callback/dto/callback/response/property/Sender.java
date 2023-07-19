package com.sharetreats01.viber_chatbot.callback.dto.callback.response.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Sender {
    private final String name;
    private final String avatar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sender sender = (Sender) o;

        if (!name.equals(sender.name)) return false;
        return avatar.equals(sender.avatar);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + avatar.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Sender{" +
                "name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
