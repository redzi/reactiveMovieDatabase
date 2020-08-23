package com.redzi.movie.database.redis;

import com.redzi.movie.database.api.response.DetailedInfoResponse;
import com.redzi.movie.database.redis.configuration.RedisTTLConfiguration;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class DetailedInfoRedisCache extends GetCachedOrLoad<DetailedInfoResponse>
{
    public DetailedInfoRedisCache(
            ReactiveRedisTemplate<String, DetailedInfoResponse> reactiveRedisTemplate,
                                  RedisTTLConfiguration redisTTLConfiguration)
    {
        super(reactiveRedisTemplate, redisTTLConfiguration);
    }
}
