package com.junzhou.infop.handler.handler;

import com.junzhou.infop.enums.AnchorState;
import com.junzhou.infop.handler.utils.LogUtils;
import com.junzhou.infop.pipeline.domain.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class BaseHandler implements Handler {

    protected Integer channelCode;
    @Autowired
    HandlerRouter handlerRouter;

    @Autowired
    LogUtils logUtils;

    @PostConstruct
    private void init() {
        handlerRouter.addHanlder(channelCode, this);
    }

    @Override
    public void doHandle(TaskInfo taskInfo) {
        if (handle(taskInfo)) {
            logUtils.print(taskInfo, AnchorState.SEND_SUCCESS);
            return;
        }
        logUtils.print(taskInfo, AnchorState.SEND_FAIL);
    }

    public abstract boolean handle(TaskInfo taskInfo);

}
