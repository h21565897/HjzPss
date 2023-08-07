package com.junzhou.infop.handler.handler;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HandlerRouter {
    private Map<Integer, Handler> handlers = new HashMap<>();

    public void addHanlder(Integer channelCode, Handler handler) {
        handlers.put(channelCode, handler);
    }

    public Handler route(Integer channelCode) {
        return handlers.get(channelCode);
    }
}
