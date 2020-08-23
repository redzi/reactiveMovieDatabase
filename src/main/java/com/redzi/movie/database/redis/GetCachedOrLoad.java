package com.redzi.movie.database.redis;

import com.redzi.movie.database.redis.configuration.RedisTTLConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public abstract class GetCachedOrLoad<R>
{
    private static final String GETTING_CACHED_DATA_IF_POPULATED = "Getting cached data if populated.";
    private static final String GETTING_LIVE_DATA_IF_NEEDED = "Getting live data if needed.";
    private static final String WARN_MESSAGE = "Redis error while trying to store data in cache.";

    private final ReactiveValueOperations<String, R> reactiveValueOperations;
    private final RedisTTLConfiguration redisTTLConfiguration;

    public GetCachedOrLoad(
            ReactiveRedisTemplate<String, R> reactiveValueOperations,
            RedisTTLConfiguration redisTTLConfiguration)
    {
        this.reactiveValueOperations = reactiveValueOperations.opsForValue();
        this.redisTTLConfiguration = redisTTLConfiguration;
    }

    public Mono<R> getCachedOrLoad(String key, Mono<? extends R> loader)
    {
        return reactiveValueOperations.get(key)
                .checkpoint(GETTING_CACHED_DATA_IF_POPULATED)
                .onErrorResume(error -> loader)
                .switchIfEmpty(loader)
                .checkpoint(GETTING_LIVE_DATA_IF_NEEDED)
                .doOnNext(value -> reactiveValueOperations.setIfAbsent(key, value, Duration.ofSeconds(redisTTLConfiguration.getSecondsTTL()))
                        .doOnError(error -> log.warn(WARN_MESSAGE, error))
                        .subscribe());
    }
}
