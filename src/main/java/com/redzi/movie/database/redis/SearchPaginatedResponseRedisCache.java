package com.redzi.movie.database.redis;

import com.redzi.movie.database.redis.configuration.RedisTTLConfiguration;
import com.redzi.movie.database.service.movie.search.data.SearchPaginatedResponse;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class SearchPaginatedResponseRedisCache extends GetCachedOrLoad<SearchPaginatedResponse>
{
    public SearchPaginatedResponseRedisCache(ReactiveRedisTemplate<String, SearchPaginatedResponse> reactiveRedisTemplate,
                                             RedisTTLConfiguration redisTTLConfiguration)
    {
        super(reactiveRedisTemplate, redisTTLConfiguration);
    }
}
