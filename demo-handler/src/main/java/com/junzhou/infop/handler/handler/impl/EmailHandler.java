package com.junzhou.infop.handler.handler.impl;

import com.junzhou.infop.handler.handler.BaseHandler;
import com.junzhou.infop.pipeline.domain.TaskInfo;
import com.junzhou.infop.pipeline.enums.ChannelCode;
import com.junzhou.infop.pipeline.model.EmailContentModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmailHandler extends BaseHandler {

    public EmailHandler() {
        channelCode = ChannelCode.EMAIL.getCode();
    }

    @Override
    public void handle(TaskInfo taskInfo) {
        List<Integer> li = new ArrayList<>();
        EmailContentModel emailContentModel = (EmailContentModel) taskInfo.getContentModel();
        System.out.println("ready to send!");
        System.out.println(emailContentModel.toString());
    }
}
