package com.redzi.movie.database.redis;

import com.redzi.movie.database.service.movie.search.data.SearchPaginatedResponse;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public interface GetCachedOrLoad<T, R extends Publisher<T>>
{
    Mono<SearchPaginatedResponse> getCachedOrLoad(String key, R loader);
}
