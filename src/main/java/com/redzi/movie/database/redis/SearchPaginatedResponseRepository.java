package com.redzi.movie.database.redis;

import com.redzi.movie.database.api.request.InitialPaginatedInfoRequest;
import com.redzi.movie.database.service.movie.search.connection.OMDBSearchConnectionProvider;
import com.redzi.movie.database.service.movie.search.data.SearchDetails;
import com.redzi.movie.database.service.movie.search.data.SearchPaginatedResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class SearchPaginatedResponseRepository implements CachedRepository<SearchDetails>
{
    private final GetCachedOrLoad<SearchPaginatedResponse, Mono<SearchPaginatedResponse>> cachedOrLoad;
    private final OMDBSearchConnectionProvider omdbSearchConnectionProvider;

    public SearchPaginatedResponseRepository(GetCachedOrLoad<SearchPaginatedResponse, Mono<SearchPaginatedResponse>> cachedOrLoad,
                                             OMDBSearchConnectionProvider omdbSearchConnectionProvider)
    {
        this.cachedOrLoad = cachedOrLoad;
        this.omdbSearchConnectionProvider = omdbSearchConnectionProvider;
    }

    @Override
    public Mono<SearchPaginatedResponse> getFromCacheOrLive(InitialPaginatedInfoRequest initialPaginatedInfoRequest)
    {
        return cachedOrLoad.getCachedOrLoad(initialPaginatedInfoRequest.getKey(), getRealData(initialPaginatedInfoRequest));
    }

    private Mono<SearchPaginatedResponse> getRealData(InitialPaginatedInfoRequest initialPaginatedInfoRequest)
    {
        return omdbSearchConnectionProvider.search(initialPaginatedInfoRequest)
                .log("Got response from live service.");
    }
}
