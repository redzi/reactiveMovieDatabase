package com.redzi.movie.database.service.movie.search;

import com.redzi.movie.database.api.request.DetailedInfoRequest;
import com.redzi.movie.database.api.request.InitialPaginatedInfoRequest;
import com.redzi.movie.database.api.response.DetailedInfoResponse;
import com.redzi.movie.database.redis.DetailedInfoRepository;
import com.redzi.movie.database.redis.SearchPaginatedResponseRepository;
import com.redzi.movie.database.service.movie.search.data.SearchPaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class OMDBSearchService implements SearchService
{
    @Autowired
    private SearchPaginatedResponseRepository searchPaginatedResponseRepository;
    @Autowired
    private DetailedInfoRepository detailedInfoRepository;

    @Override
    public Mono<SearchPaginatedResponse> searchPaginatedResults(InitialPaginatedInfoRequest initialPaginatedInfoRequest)
    {
        return searchPaginatedResponseRepository.getFromCacheOrLive(initialPaginatedInfoRequest)
                .log("Got search paginated results.")
                .checkpoint(getClass().getSimpleName());
    }

    @Override
    public Mono<DetailedInfoResponse> fetchSpecifics(DetailedInfoRequest detailedRequest)
    {
        return detailedInfoRepository.getFromCacheOrLive(detailedRequest);
    }
}
