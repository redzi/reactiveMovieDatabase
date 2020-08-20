package com.redzi.movie.database.redis;

import com.redzi.movie.database.redis.configuration.RedisTTLConfiguration;
import com.redzi.movie.database.service.movie.search.data.SearchPaginatedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Component
public class SearchPaginatedResponseRedisCache implements GetCachedOrLoad<SearchPaginatedResponse, Mono<SearchPaginatedResponse>>
{
    private final ReactiveValueOperations<String, SearchPaginatedResponse> reactiveValueOperations;
    private final RedisTTLConfiguration redisTTLConfiguration;

    public SearchPaginatedResponseRedisCache(ReactiveRedisTemplate<String, SearchPaginatedResponse> reactiveRedisTemplate,
                                             RedisTTLConfiguration redisTTLConfiguration)
    {
        this.reactiveValueOperations = reactiveRedisTemplate.opsForValue();
        this.redisTTLConfiguration = redisTTLConfiguration;
    }

    @Override
    public Mono<SearchPaginatedResponse> getCachedOrLoad(String key, Mono<SearchPaginatedResponse> loader)
    {
        return reactiveValueOperations.get(key)
                .checkpoint("Getting cached data if populated.")
                .onErrorResume(error -> loader)
                .switchIfEmpty(loader)
                .checkpoint("Getting live data if needed.")
                .doOnNext(value -> reactiveValueOperations.setIfAbsent(key, value, Duration.ofSeconds(redisTTLConfiguration.getSecondsTTL()))
                        .doOnError(error -> log.warn("Redis error while trying to store data in cache.", error))
                        .subscribe());
    }
}
