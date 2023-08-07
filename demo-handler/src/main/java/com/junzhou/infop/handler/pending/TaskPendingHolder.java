package com.junzhou.infop.handler.pending;

import com.junzhou.infop.handler.utils.GroupIdMappingUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TaskPendingHolder {

    private Map<String, ExecutorService> taskPendingHolder = new HashMap<>();

    private List<String> groupIds = GroupIdMappingUtils.getAllGroupIds();

    @PostConstruct
    public void init() {
        for (var groupId : groupIds) {
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            taskPendingHolder.put(groupId, executorService);
        }
    }

    public ExecutorService route(String groupId) {
        return taskPendingHolder.get(groupId);
    }
}
