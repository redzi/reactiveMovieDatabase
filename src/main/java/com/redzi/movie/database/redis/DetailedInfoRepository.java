package com.redzi.movie.database.redis;

import com.redzi.movie.database.api.request.DetailedInfoRequest;
import com.redzi.movie.database.api.response.DetailedInfoResponse;
import com.redzi.movie.database.service.movie.search.connection.OMDBSearchSpecificConnectionProvider;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DetailedInfoRepository extends CachedRepository<DetailedInfoRequest, DetailedInfoResponse>
{
    private final OMDBSearchSpecificConnectionProvider omdbSearchSpecificConnectionProvider;

    public DetailedInfoRepository(GetCachedOrLoad<DetailedInfoResponse> cachedOrLoad,
                                  OMDBSearchSpecificConnectionProvider omdbSearchSpecificConnectionProvider)
    {
        super(cachedOrLoad);
        this.omdbSearchSpecificConnectionProvider = omdbSearchSpecificConnectionProvider;
    }

    @Override
    protected Mono<DetailedInfoResponse> getRealData(DetailedInfoRequest keyContainer)
    {
        return omdbSearchSpecificConnectionProvider.search(keyContainer);
    }
}
