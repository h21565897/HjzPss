package com.junzhou.infop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespStatusEnum {
    SUCCESS("0", "operation success"),
    FAIL("1", "operation fail");


    private final String code;
    private final String msg;
}
