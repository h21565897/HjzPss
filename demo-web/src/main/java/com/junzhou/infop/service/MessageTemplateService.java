package com.junzhou.infop.service;

import com.junzhou.infop.service.api.entity.MessageTemplate;

import java.util.List;

public interface MessageTemplateService {
    MessageTemplate saveOrUpdate(MessageTemplate messageTemplate);

    MessageTemplate queryById(Long id);

    void deleteById(Long id);

    List<MessageTemplate> listAll(String creator);
}
