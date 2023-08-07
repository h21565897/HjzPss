package com.junzhou.infop.pipeline.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.junzhou.infop.pipeline.BusinessProcess;
import com.junzhou.infop.pipeline.ProcessContext;
import com.junzhou.infop.pipeline.domain.SendTaskModel;
import com.junzhou.infop.pipeline.enums.BusinessCode;
import com.junzhou.infop.service.api.service.SendMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendToMqAction implements BusinessProcess<SendTaskModel> {

    @Value("${infop.topic.name}")
    private String sendMessageTopic;
    @Value("${infop.topic.tagId.value:infop.topic}")
    private String tagId;
    @Autowired
    private SendMqService sendMqService;

    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        SendTaskModel sendTaskModel = context.getProcessModel();
        if (BusinessCode.COMMON_SEND.getCode().equals(context.getCode())) {
            String message = JSON.toJSONString(sendTaskModel.getTaskInfo(), new SerializerFeature[]{SerializerFeature.WriteClassName});
            sendMqService.send(sendMessageTopic, message, tagId);
        }
    }
}
