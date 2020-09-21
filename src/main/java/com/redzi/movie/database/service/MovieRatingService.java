package com.redzi.movie.database.service;

import com.redzi.movie.database.api.request.DetailedInfoRequest;
import com.redzi.movie.database.api.request.InitialPaginatedInfoRequest;
import com.redzi.movie.database.api.response.DetailedInfoResponse;
import com.redzi.movie.database.service.movie.search.OMDBSearchService;
import com.redzi.movie.database.api.response.SearchDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieRatingService
{
    @Autowired
    private OMDBSearchService omdbService;

    public Flux<SearchDetails> fetchGeneralPaginatedInfo(InitialPaginatedInfoRequest initialPaginatedInfoRequest)
    {
        return omdbService.searchPaginatedResults(initialPaginatedInfoRequest)
                .checkpoint(getClass().getSimpleName());
    }

    public Mono<DetailedInfoResponse> fetchSpecifics(DetailedInfoRequest detailedRequest)
    {
        return omdbService.fetchSpecifics(detailedRequest)
                .checkpoint(getClass().getSimpleName());
    }
}
