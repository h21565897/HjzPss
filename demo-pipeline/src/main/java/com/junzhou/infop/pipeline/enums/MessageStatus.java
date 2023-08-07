package com.junzhou.infop.pipeline.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum MessageStatus {
    /**
     * 10.新建
     */
    INIT(10, "inti status"),
    /**
     * 20.停用
     */
    STOP(20, "unavailable"),
    /**
     * 30.启用
     */
    RUN(30, "available"),
    /**
     * 40.等待发送
     */
    PENDING(40, "pending"),
    /**
     * 50.发送中
     */
    SENDING(50, "sending"),
    /**
     * 60.发送成功
     */
    SEND_SUCCESS(60, "send success"),
    /**
     * 70.发送失败
     */
    SEND_FAIL(70, "send failed");
    private final Integer code;
    private final String description;
}
