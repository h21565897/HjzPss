package com.junzhou.infop.enums;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@ToString
@AllArgsConstructor
@Getter
@JSONType(serializeEnumAsJavaBean = true)
public enum AnchorState {
    RECEIVER(10, "message queue receive success"), DISCARD(20, "message queue discarded"), SEND_SUCCESS(60, "send success"), SEND_FAIL(70, "send fail");

    private final Integer code;

    private final String description;

    @JSONCreator
    public static AnchorState from(String objectStr) {
        JSONObject jsonObject = JSON.parseObject(objectStr);
        int code = jsonObject.getInteger("code");
        for (AnchorState v : values()) {
            if (v.code == code) {
                return v;
            }
        }
        throw new IllegalArgumentException("code " + code);
    }
}
