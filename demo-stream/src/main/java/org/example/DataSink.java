package org.example;


import com.alibaba.fastjson.JSON;
import com.junzhou.infop.handler.enums.AnchorInfo;
import com.junzhou.infop.utils.TraceUtils;
import io.lettuce.core.RedisFuture;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class DataSink implements SinkFunction<AnchorInfo> {


    @Override
    public void invoke(AnchorInfo value, Context context) throws Exception {
        System.out.println("sink");
        if (value.getSimpleTaskInfo().getUserObj() != null) RedisUtils.executeAsyncCommands((commands) -> {
            System.out.println("executing");
            List<RedisFuture<?>> futures = new ArrayList<>();
            String redisKey = TraceUtils.generateRedisKeyWithUserId(value.getSimpleTaskInfo().getUserObj());
            futures.add(commands.lpush(redisKey, JSON.toJSONString(value)));
            futures.add(commands.expire(redisKey, Duration.ofDays(1).toSeconds()));
            return futures;
        });

    }
}
