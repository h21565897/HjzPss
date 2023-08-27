package com.junzhou.infop.handler.utils;


import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.junzhou.infop.enums.AnchorState;
import com.junzhou.infop.handler.enums.AnchorInfo;
import com.junzhou.infop.handler.enums.SimpleTaskInfo;
import com.junzhou.infop.pipeline.domain.TaskInfo;
import com.junzhou.infop.service.api.service.SendMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogUtils {

    @Autowired
    SendMqService sendMqService;
    @Value("${junzhou.infop.log.topic.name:infopLog}")
    private String topicName;

    public void print(AnchorInfo anchorInfo) {
        anchorInfo.setLogTimestamp(DateUtil.currentSeconds());
        String message = JSON.toJSONString(anchorInfo);
        sendMqService.send(topicName, message);
        log.info(message);
    }

    public void print(TaskInfo taskInfo, AnchorState state) {
        AnchorInfo anchorInfo = AnchorInfo.builder().logTimestamp(DateUtil.currentSeconds()).simpleTaskInfo(SimpleTaskInfo.builder().contentModel(JSON.toJSONString(taskInfo.getContentModel())).messageTemplateId(taskInfo.getMessageTemplateId()).receiver(taskInfo.getReceiver()).sendAccountId(taskInfo.getSendAccountId()).userObj(taskInfo.getUserObj()).messageId(taskInfo.getMessageId()).build()).state(state).build();
        String message = JSON.toJSONString(anchorInfo);
        sendMqService.send(topicName, message);
        log.info(message);
    }


}
