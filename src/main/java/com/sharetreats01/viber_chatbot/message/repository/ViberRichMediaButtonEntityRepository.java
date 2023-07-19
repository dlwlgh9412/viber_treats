package com.sharetreats01.viber_chatbot.message.repository;

import com.sharetreats01.viber_chatbot.message.entity.ViberRichMediaButtonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViberRichMediaButtonEntityRepository extends JpaRepository<ViberRichMediaButtonEntity, Long> {
    List<ViberRichMediaButtonEntity> findAllByRichMedia_Id(Long richMediaId);
}
