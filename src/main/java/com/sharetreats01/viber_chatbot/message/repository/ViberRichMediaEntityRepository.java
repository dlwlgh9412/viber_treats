package com.sharetreats01.viber_chatbot.message.repository;

import com.sharetreats01.viber_chatbot.message.entity.ViberRichMediaEntity;
import com.sharetreats01.viber_chatbot.message.enums.RichMediaType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ViberRichMediaEntityRepository extends JpaRepository<ViberRichMediaEntity, Long> {
    Optional<ViberRichMediaEntity> findTopByMetaDataTypeOrderByCreatedAtDesc(RichMediaType type);
}
