package com.junzhou.infop.handler.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.junzhou.infop.handler.Task;
import com.junzhou.infop.handler.pending.TaskPendingHolder;
import com.junzhou.infop.handler.service.ConsumeService;
import com.junzhou.infop.handler.utils.GroupIdMappingUtils;
import com.junzhou.infop.pipeline.domain.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsumeServiceImpl implements ConsumeService {
    @Autowired
    ApplicationContext context;

    @Autowired
    TaskPendingHolder taskPendingHolder;

    @Override
    public void consume2Send(List<TaskInfo> taskInfoList) {
        String topicGroupId = GroupIdMappingUtils.getGroupIdByTaskInfo(CollUtil.getFirst(taskInfoList.iterator()));
        for (var taskinfo : taskInfoList) {
            Task task = context.getBean(Task.class).setTaskinfo(taskinfo);
            taskPendingHolder.route(topicGroupId).execute(task);
        }
    }
}
