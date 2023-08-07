package com.junzhou.infop.handler.handler;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class BaseHandler implements Handler {

    protected Integer channelCode;
    @Autowired
    HandlerRouter handlerRouter;

    @PostConstruct
    private void init() {
        handlerRouter.addHanlder(channelCode, this);
    }
}
