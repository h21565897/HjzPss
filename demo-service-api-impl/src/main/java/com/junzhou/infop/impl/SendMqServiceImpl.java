package com.junzhou.infop.impl;

import cn.hutool.core.util.StrUtil;
import com.junzhou.infop.service.api.service.SendMqService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
public class SendMqServiceImpl implements SendMqService {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${infop.topic.tagId.key:kafkatagId}")
    private String tagIdKey;

    @Override
    public void send(String topic, String value) {
        send(topic, value, null);
    }

    @Override
    public void send(String topic, String value, String tagId) {
        if (StrUtil.isNotBlank(tagId)) {
            List<RecordHeader> headers = Arrays.asList(new RecordHeader(tagIdKey, tagId.getBytes(StandardCharsets.UTF_8)));
            kafkaTemplate.send(new ProducerRecord(topic, null, null, null, value, headers));
            return;
        }
        kafkaTemplate.send(topic, value);
    }
}
