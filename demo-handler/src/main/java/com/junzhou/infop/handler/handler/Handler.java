package com.junzhou.infop.handler.handler;

import com.junzhou.infop.pipeline.domain.TaskInfo;

public interface Handler {
    void handle(TaskInfo taskInfo);
}
