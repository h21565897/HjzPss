package com.junzhou.infop;

import io.lettuce.core.LettuceFutures;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class RedisUtils {
    private static RedisClient redisClient;
    private static StatefulRedisConnection<String, String> connect;
    private static RedisAsyncCommands<String, String> asyncCommands;

    static {
//        RedisURI redisUri = RedisURI.Builder.redis("127.0.0.1")
//                .withPort(6379)
//                .withPassword("austin".toCharArray())
//                .build();
        redisClient = RedisClient.create("redis://austin@localhost:6380/0");
        connect = redisClient.connect();
        asyncCommands = connect.async();

    }

    public static RedisClient getRedisClient() {
        return redisClient;
    }

    public static void executeAsyncCommands(Function<RedisAsyncCommands, List<RedisFuture<?>>> callback) {

        List<RedisFuture<?>> futures = callback.apply(asyncCommands);
        connect.flushCommands();
        LettuceFutures.awaitAll(10, TimeUnit.SECONDS, futures.toArray(new RedisFuture[futures.size()]));
    }
}