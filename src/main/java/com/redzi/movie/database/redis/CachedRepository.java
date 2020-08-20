package com.redzi.movie.database.redis;

import com.redzi.movie.database.api.request.InitialPaginatedInfoRequest;
import com.redzi.movie.database.service.movie.search.data.SearchPaginatedResponse;
import reactor.core.publisher.Mono;

public interface CachedRepository<T>
{
    Mono<SearchPaginatedResponse> getFromCacheOrLive(InitialPaginatedInfoRequest initialPaginatedInfoRequest);
}
