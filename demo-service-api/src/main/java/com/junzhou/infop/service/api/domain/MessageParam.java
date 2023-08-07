package com.junzhou.infop.service.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageParam {

    private String bizId;

    private String receiver;

    private Map<String, String> variables;

    private Map<String, String> extra;

}
