package com.junzhou.infop;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.junzhou.infop.enums.AnchorState;
import com.junzhou.infop.handler.utils.LogUtils;
import io.lettuce.core.RedisFuture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class kafkatest {
    @Autowired
    LogUtils logUtils;

    @Autowired
    KafkaTemplate kafkaTemplate;


    @Test
    public void test2() {
        for (int i = 0; i < 10000; i++) {
            kafkaTemplate.send("infopLog", "{ \"logTimestamp\": 1692325570, \"state\": 10 }");
        }
    }

    @Test
    public void SimpleTest() throws JsonProcessingException, InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100000; i++) {
                        RedisUtils.executeAsyncCommands((commands) -> {
                            List<RedisFuture<?>> futures = new ArrayList<>();
                            futures.add(commands.lpush("fff", "888"));
                            return futures;
                        });
                    }
                }
            });
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.join();
        }

    }

    @Test
    public void test3() {
        AnchorState state = AnchorState.RECEIVER;
        String jsonString = JSON.toJSONString(state);
        System.out.println(jsonString);
        AnchorState anchorState = JSON.parseObject(jsonString, AnchorState.class);
        System.out.println(anchorState);

    }
}
