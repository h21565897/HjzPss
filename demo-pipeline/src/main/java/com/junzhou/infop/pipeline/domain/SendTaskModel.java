package com.junzhou.infop.pipeline.domain;

import com.junzhou.infop.pipeline.ProcessModel;
import com.junzhou.infop.service.api.domain.MessageParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendTaskModel implements ProcessModel {
    private Long messageTemplateId;

    private List<MessageParam> messageParamList;

    private List<TaskInfo> taskInfo;


}
