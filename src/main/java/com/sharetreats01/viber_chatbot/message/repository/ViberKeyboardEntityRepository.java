package com.sharetreats01.viber_chatbot.message.repository;

import com.sharetreats01.viber_chatbot.message.entity.ViberKeyboardEntity;
import com.sharetreats01.viber_chatbot.message.enums.KeyboardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ViberKeyboardEntityRepository extends JpaRepository<ViberKeyboardEntity, Long> {
    Optional<ViberKeyboardEntity> findTopByMetaDataTypeOrderByCreatedAtDesc(KeyboardType type);
}
