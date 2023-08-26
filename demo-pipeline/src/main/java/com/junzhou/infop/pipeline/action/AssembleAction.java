package com.junzhou.infop.pipeline.action;


import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.junzhou.infop.pipeline.BusinessProcess;
import com.junzhou.infop.pipeline.ProcessContext;
import com.junzhou.infop.pipeline.domain.SendTaskModel;
import com.junzhou.infop.pipeline.domain.TaskInfo;
import com.junzhou.infop.pipeline.enums.BusinessCode;
import com.junzhou.infop.pipeline.enums.ChannelCode;
import com.junzhou.infop.pipeline.model.ContentModel;
import com.junzhou.infop.service.api.dao.MessageTemplateDao;
import com.junzhou.infop.service.api.domain.MessageParam;
import com.junzhou.infop.service.api.entity.MessageTemplate;
import com.junzhou.infop.vo.BasicResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class AssembleAction implements BusinessProcess<SendTaskModel> {

    @Autowired
    MessageTemplateDao messageTemplateDao;

    private static ContentModel getContentModelValue(MessageTemplate messageTemplate, MessageParam messageParam) {
        Integer sendChannel = messageTemplate.getSendChannel();
        var contentModelClass = ChannelCode.getChannelModelClassByCode(sendChannel);
        JSONObject jsonObject = JSON.parseObject(messageParam.getMsgContent());
        //assemble contentModel with reflection
        Field[] fields = ReflectUtil.getFields(contentModelClass);
        ContentModel contentModel = ReflectUtil.newInstance(contentModelClass);
        for (var field : fields) {
            String originalField = jsonObject.getString(field.getName());
            if (StrUtil.isNotBlank(originalField)) {
                ReflectUtil.setFieldValue(contentModel, field, originalField);
            }
        }
        return contentModel;
    }

    @Override
    public void process(ProcessContext<SendTaskModel> context) {

        SendTaskModel sendTaskModel = context.getProcessModel();
        Long messageTemplateId = sendTaskModel.getMessageTemplateId();
        Optional<MessageTemplate> messageTemplate = messageTemplateDao.findById(messageTemplateId);
        if (messageTemplate.isEmpty()) {
            context.setNeedBreak(true).setResponse(BasicResultVo.fail("Template not found"));
            return;
        }
        if (BusinessCode.COMMON_SEND.getCode().equals(context.getCode())) {
            List<TaskInfo> taskInfos = assembleTaskInfo(sendTaskModel, messageTemplate.get());
            sendTaskModel.setTaskInfo(taskInfos);
        }

    }

    private List<TaskInfo> assembleTaskInfo(SendTaskModel sendTaskModel, MessageTemplate messageTemplate) {
        List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();
        List<TaskInfo> taskInfoList = new ArrayList<>();
        for (var messageParam : messageParamList) {
            TaskInfo taskInfo = TaskInfo.builder()
                    .contentModel(getContentModelValue(messageTemplate, messageParam))
                    .messageId(IdUtil.nanoId())
                    .bizId(messageParam.getBizId())
                    .messageTemplateId(messageTemplate.getId())
                    .receiver(new HashSet<>(Arrays.asList(messageParam.getReceiver().split(","))))
                    .sendIdType(messageTemplate.getSendIdType())
                    .sendChannelId(messageTemplate.getSendChannel())
                    .sendAccountId(messageTemplate.getSendAccountId())
                    .userObj(sendTaskModel.getUserObj())
                    .build();
            if (StrUtil.isBlank(taskInfo.getBizId())) {
                taskInfo.setBizId(taskInfo.getMessageId());
            }
            taskInfoList.add((taskInfo));
        }
        return taskInfoList;

    }

}
