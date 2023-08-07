package com.junzhou.infop.impl;

import com.junzhou.infop.enums.RespStatusEnum;
import com.junzhou.infop.pipeline.ProcessContext;
import com.junzhou.infop.pipeline.ProcessController;
import com.junzhou.infop.pipeline.domain.SendTaskModel;
import com.junzhou.infop.service.api.domain.SendRequest;
import com.junzhou.infop.service.api.domain.SendResponse;
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
    public SendResponse send(SendRequest sendRequest) {
        if (ObjectUtils.isEmpty(sendRequest)) {
            return new SendResponse(RespStatusEnum.FAIL.getCode(), RespStatusEnum.FAIL.getMsg());
        }

        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(sendRequest.getMessageTemplateId())
                .messageParamList(Collections.singletonList(sendRequest.getMessageParam()))
                .build();
        ProcessContext context = ProcessContext.builder()
                .code(sendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVo.success()).build();
        processController.process(context);

        return new SendResponse(context.getResponse().getStatus(), context.getResponse().getMsg());
    }
}
