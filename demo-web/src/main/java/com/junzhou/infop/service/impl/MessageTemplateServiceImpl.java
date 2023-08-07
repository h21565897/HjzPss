package com.junzhou.infop.service.impl;

import cn.hutool.core.date.DateUtil;
import com.junzhou.infop.pipeline.enums.MessageStatus;
import com.junzhou.infop.service.MessageTemplateService;
import com.junzhou.infop.service.api.dao.MessageTemplateDao;
import com.junzhou.infop.service.api.entity.MessageTemplate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class MessageTemplateServiceImpl implements MessageTemplateService {
    MessageTemplateDao messageTemplateDao;


    private void initStatus(MessageTemplate messageTemplate) {
        messageTemplate.setMsgStatus(MessageStatus.INIT.getCode())
                .setCreated(Math.toIntExact(DateUtil.currentSeconds()));
    }

    @Override
    public MessageTemplate saveOrUpdate(MessageTemplate messageTemplate) {
        if (Objects.isNull(messageTemplate.getId())) {
            initStatus(messageTemplate);
        }
        messageTemplate.setUpdated(Math.toIntExact(DateUtil.currentSeconds()));
        return messageTemplateDao.save(messageTemplate);
    }

    @Override
    public MessageTemplate queryById(Long id) {
        return messageTemplateDao.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        messageTemplateDao.deleteById(id);
    }

    @Override
    public List<MessageTemplate> listAll(String creator) {
        return messageTemplateDao.findAllByCreatorEquals(creator);
    }
}
