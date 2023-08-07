package com.junzhou.infop.service.api.dao;

import com.junzhou.infop.service.api.entity.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageTemplateDao extends JpaRepository<MessageTemplate, Long> {
    List<MessageTemplate> findAllByCreatorEquals(String creator);
}
