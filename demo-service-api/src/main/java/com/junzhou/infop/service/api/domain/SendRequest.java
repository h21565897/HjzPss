package com.junzhou.infop.service.api.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SendRequest {

    private String code;
    private Long messageTemplateId;

    private MessageParam messageParam;
}
