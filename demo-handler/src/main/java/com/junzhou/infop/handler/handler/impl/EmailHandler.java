package com.junzhou.infop.handler.handler.impl;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.junzhou.infop.handler.handler.BaseHandler;
import com.junzhou.infop.handler.utils.AccountUtils;
import com.junzhou.infop.pipeline.domain.TaskInfo;
import com.junzhou.infop.pipeline.enums.ChannelCode;
import com.junzhou.infop.pipeline.model.EmailContentModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailHandler extends BaseHandler {

    @Autowired
    AccountUtils accountUtils;

    public EmailHandler() {
        channelCode = ChannelCode.EMAIL.getCode();
    }

    @Override
    public boolean handle(TaskInfo taskInfo) {
        try {
            EmailContentModel emailContentModel = (EmailContentModel) taskInfo.getContentModel();
            System.out.println("ready to send!");
            System.out.println(emailContentModel.toString());
            MailAccount account = accountUtils.getAccountById(taskInfo.getSendAccountId(), MailAccount.class);
            account.setSslEnable(true);
            account.setAuth(true);
            account.setDebug(true);
            account.setStarttlsEnable(true);
            MailUtil.send(account, taskInfo.getReceiver(), emailContentModel.getTitle(), emailContentModel.getContent(), true);
        } catch (Exception e) {
            log.error("EmailHandler#handler fail!{},params:{}", ExceptionUtil.stacktraceToString(e), taskInfo);
            return false;
        }
        return true;

    }
}
