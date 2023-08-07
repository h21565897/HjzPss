package com.junzhou.infop.pipeline.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum BusinessCode {
    COMMON_SEND("send", "send a message"),
    RECALL("recall", "recall a message");

    private final String code;

    private final String description;
}
