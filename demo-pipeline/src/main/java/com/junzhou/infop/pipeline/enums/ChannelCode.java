package com.junzhou.infop.pipeline.enums;

import com.junzhou.infop.pipeline.model.ContentModel;
import com.junzhou.infop.pipeline.model.EmailContentModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor
public enum ChannelCode {
    EMAIL(40, "email", EmailContentModel.class, "email"),
    DEFAULT(10, "default", ContentModel.class, "default");

    private final Integer code;

    private final String description;

    private final Class<? extends ContentModel> contentModelClass;

    private final String EN;

    public static Class<? extends ContentModel> getChannelModelClassByCode(Integer code) {
        return Arrays.stream(values()).filter(channelCode -> Objects.equals(code, channelCode.getCode())).map(ChannelCode::getContentModelClass).findFirst()
                .orElse(null);
    }
}
