package com.junzhou.infop.handler.receiver.kafka;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.junzhou.infop.handler.service.ConsumeService;
import com.junzhou.infop.handler.utils.GroupIdMappingUtils;
import com.junzhou.infop.pipeline.domain.TaskInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)

public class Receiver {
    @Autowired
    private ConsumeService consumeService;

    @KafkaListener(topics = "#{'${infop.topic.name}'}")
    public void consumer(ConsumerRecord<?, String> consumerRecord, @Header(KafkaHeaders.GROUP_ID) String topicGroupId) {
        String kafkaMessage = consumerRecord.value();
        System.out.println("Print at Receiver");
        System.out.println(kafkaMessage);
        if (!ObjectUtil.isEmpty(kafkaMessage)) {
            List<TaskInfo> taskInfoLists = JSON.parseArray(kafkaMessage, TaskInfo.class);
            String messageGroupId = GroupIdMappingUtils.getGroupIdByTaskInfo(CollUtil.getFirst(taskInfoLists.iterator()));
            if (Objects.equals(topicGroupId, messageGroupId)) {
                consumeService.consume2Send(taskInfoLists);
            }
        }
    }
}
