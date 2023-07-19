package com.sharetreats01.viber_chatbot.viber.repository;

import com.sharetreats01.viber_chatbot.viber.exception.ViberException;
import com.sharetreats01.viber_chatbot.viber.entity.ViberWebhookSettingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ViberRepositoryImpl implements ViberRepository {
    private final EntityManager em;

    @Override
    public ViberWebhookSettingEntity findRecentWebhookSetting() {
        return em.createQuery(
                        "select ws " +
                                "from ViberWebhookSettingEntity ws " +
                                "order by ws.createdAt desc ",
                        ViberWebhookSettingEntity.class)
                .getResultList().stream().findFirst().orElseThrow(() -> new ViberException("웹훅 설정이 없습니다. 어플리케이션이 종료됩니다."));
    }
}
