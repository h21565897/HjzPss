package com.junzhou.infop.handler.service;

import com.junzhou.infop.pipeline.domain.TaskInfo;

import java.util.List;


public interface ConsumeService {
    void consume2Send(List<TaskInfo> taskInfoList);
}
