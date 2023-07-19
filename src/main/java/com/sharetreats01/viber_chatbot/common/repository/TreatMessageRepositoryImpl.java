package com.sharetreats01.viber_chatbot.common.repository;

import com.sharetreats01.viber_chatbot.callback.enums.TreatState;
import com.sharetreats01.viber_chatbot.callback.enums.TreatFlag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TreatMessageRepositoryImpl implements TreatMessageRepository {
    private final EntityManager entityManager;

    @Override
    public Optional<String> findSuccessFirstByConstantAndVersion(TreatState constant) {
        return entityManager.createQuery(
                        "select tm.template " +
                                "from TreatMessageTemplateEntity tm " +
                                "where tm.type =: type and tm.constant =: constant " +
                                "order by tm.version desc",
                        String.class)
                .setParameter("type", TreatFlag.SUCCESS)
                .setParameter("constant", constant)
                .getResultList().stream().findFirst();
    }

    @Override
    public Optional<String> findFailureFirstByConstantAndVersion(TreatState constant) {
        return entityManager.createQuery(
                        "select tm.template " +
                                "from TreatMessageTemplateEntity tm " +
                                "where tm.type =: type and tm.constant =: constant " +
                                "order by tm.version desc",
                        String.class)
                .setParameter("type", TreatFlag.FAILURE)
                .setParameter("constant", constant)
                .getResultList().stream().findFirst();    }
}
