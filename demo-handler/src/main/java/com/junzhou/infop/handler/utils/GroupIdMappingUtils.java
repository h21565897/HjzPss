package com.junzhou.infop.handler.utils;

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
        String channelCodeEn = String.valueOf(Arrays.stream(ChannelCode.values()).filter(channelCode -> Objects.equals(channelCode.getCode(), taskInfo.getSendChannelId())).findFirst().orElse(null
        ));
        return channelCodeEn;
    }
}
