package com.junzhou.infop.handler.enums;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleTaskInfo {
    private String userObj;
    private String contentModel;
    private Set<String> receiver;
    private Long messageTemplateId;
    private Integer sendAccountId;
    private String messageId;
}
