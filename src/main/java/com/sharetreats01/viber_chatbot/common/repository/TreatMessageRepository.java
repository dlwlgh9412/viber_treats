package com.sharetreats01.viber_chatbot.common.repository;


import com.sharetreats01.viber_chatbot.callback.enums.TreatState;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TreatMessageRepository {
    Optional<String> findSuccessFirstByConstantAndVersion(TreatState constant);
    Optional<String> findFailureFirstByConstantAndVersion(TreatState constant);

}
