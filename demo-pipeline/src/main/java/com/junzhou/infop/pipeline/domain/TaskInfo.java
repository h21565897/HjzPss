package com.junzhou.infop.pipeline.domain;

import com.junzhou.infop.pipeline.model.ContentModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TaskInfo {
    private String bizId;

    private String messageId;

    private Long messageTemplateId;

    private Long businessId;

    private Set<String> receiver;

    private Integer sendIdType;

    private Integer sendChannelId;

    private ContentModel contentModel;

    private Integer sendAccountId;

}
