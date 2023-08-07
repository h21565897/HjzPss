package com.junzhou.infop.service.api.service;

public interface SendMqService {
    void send(String topic, String value);

    void send(String topic, String value, String tagId);
}
