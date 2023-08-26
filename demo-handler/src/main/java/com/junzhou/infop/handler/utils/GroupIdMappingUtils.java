package com.junzhou.infop.handler.utils;

import cn.hutool.core.util.ObjectUtil;
import com.junzhou.infop.pipeline.domain.TaskInfo;
import com.junzhou.infop.pipeline.enums.ChannelCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GroupIdMappingUtils {
    public static List<String> getAllGroupIds() {
        List<String> groupIds = new ArrayList<>();
        for (var channelcode : ChannelCode.values()) {
            groupIds.add(channelcode.getEN());
        }
        return groupIds;
    }

    public static String getGroupIdByTaskInfo(TaskInfo taskInfo) {
        ChannelCode channelCode1 = Arrays.stream(ChannelCode.values()).filter(channelCode -> Objects.equals(channelCode.getCode(), taskInfo.getSendChannelId())).findFirst().orElse(null
        );
        if (!ObjectUtil.isEmpty(channelCode1)) {
            return channelCode1.getEN();
        }

        return "Error";
    }
}
