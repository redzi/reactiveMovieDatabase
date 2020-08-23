package com.redzi.movie.database.redis;

import com.redzi.movie.database.api.request.InitialPaginatedInfoRequest;
import com.redzi.movie.database.service.movie.search.connection.OMDBSearchGeneralConnectionProvider;
import com.redzi.movie.database.service.movie.search.data.SearchPaginatedResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class SearchPaginatedResponseRepository extends CachedRepository<InitialPaginatedInfoRequest, SearchPaginatedResponse>
{
    private final OMDBSearchGeneralConnectionProvider omdbSearchGeneralConnectionProvider;

    public SearchPaginatedResponseRepository(GetCachedOrLoad<SearchPaginatedResponse> cachedOrLoad,
                                             OMDBSearchGeneralConnectionProvider omdbSearchGeneralConnectionProvider)
    {
        super(cachedOrLoad);
        this.omdbSearchGeneralConnectionProvider = omdbSearchGeneralConnectionProvider;
    }

    @Override
    protected Mono<SearchPaginatedResponse> getRealData(InitialPaginatedInfoRequest initialPaginatedInfoRequest)
    {
        return omdbSearchGeneralConnectionProvider.search(initialPaginatedInfoRequest)
                .log("Got response from live service.");
    }
}
