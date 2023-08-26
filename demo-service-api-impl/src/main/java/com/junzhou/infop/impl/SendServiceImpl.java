package com.junzhou.infop.impl;

import com.junzhou.infop.pipeline.ProcessContext;
import com.junzhou.infop.pipeline.ProcessController;
import com.junzhou.infop.pipeline.domain.SendTaskModel;
import com.junzhou.infop.service.api.domain.SendRequest;
import com.junzhou.infop.service.api.service.SendService;
import com.junzhou.infop.vo.BasicResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;

@Service
public class SendServiceImpl implements SendService {

    @Autowired
    private ProcessController processController;

    @Override
    public BasicResultVo send(SendRequest sendRequest) {
        if (ObjectUtils.isEmpty(sendRequest)) {
            return BasicResultVo.fail("");
        }

        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(sendRequest.getMessageTemplateId())
                .userObj(sendRequest.getUserObj())
                .messageParamList(Collections.singletonList(sendRequest.getMessageParam()))
                .build();
        ProcessContext context = ProcessContext.builder()
                .code(sendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVo.success()).build();
        processController.process(context);

        return context.getResponse();
    }
}
