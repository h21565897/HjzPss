package org.example;

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

    static {
        redisClient = RedisClient.create("redis://junzhou123@redis:6379/0");
        connect = redisClient.connect();


    }

    public static RedisClient getRedisClient() {
        return redisClient;
    }

    public static void executeAsyncCommands(Function<RedisAsyncCommands, List<RedisFuture<?>>> callback) {
        System.out.println("execute");
        RedisAsyncCommands<String, String> asyncCommands = connect.async();
        List<RedisFuture<?>> futures = callback.apply(asyncCommands);
        connect.flushCommands();
        LettuceFutures.awaitAll(10, TimeUnit.SECONDS, futures.toArray(new RedisFuture[futures.size()]));
    }
}
