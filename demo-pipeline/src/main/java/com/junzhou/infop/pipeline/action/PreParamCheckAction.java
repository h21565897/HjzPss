package com.junzhou.infop.pipeline.action;

import cn.hutool.core.collection.CollUtil;
import com.junzhou.infop.pipeline.BusinessProcess;
import com.junzhou.infop.pipeline.ProcessContext;
import com.junzhou.infop.pipeline.domain.SendTaskModel;
import com.junzhou.infop.service.api.domain.MessageParam;
import com.junzhou.infop.vo.BasicResultVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreParamCheckAction implements BusinessProcess<SendTaskModel> {


    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        SendTaskModel sendTaskModel = context.getProcessModel();

        Long messageTemplateId = sendTaskModel.getMessageTemplateId();
        List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();
        // no message template id or message parameters
        if (messageTemplateId == null || CollUtil.isEmpty(messageParamList)) {
            context.setNeedBreak(true).setResponse(BasicResultVo.fail("Template id or parameter list cannot be null"));
            return;
        }
        //filter those receiver = null
        List<MessageParam> filteredMessageParamList = messageParamList.stream().filter((template) -> (!template.getReceiver().isBlank())).toList();
        if (CollUtil.isEmpty(filteredMessageParamList)) {
            context.setNeedBreak(true).setResponse(BasicResultVo.fail("All receivers in templates are wrong"));
            return;
        }


        sendTaskModel.setMessageParamList(filteredMessageParamList);


    }
}
