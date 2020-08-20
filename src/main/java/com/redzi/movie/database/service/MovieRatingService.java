package com.redzi.movie.database.service;

import com.redzi.movie.database.api.request.InitialPaginatedInfoRequest;
import com.redzi.movie.database.api.response.InitialPaginatedInfoResponse;
import com.redzi.movie.database.service.movie.search.OMDBSearchService;
import com.redzi.movie.database.service.movie.search.mapper.InitialPaginatedInfoResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MovieRatingService
{
    @Autowired
    private OMDBSearchService omdbService;
    @Autowired
    private InitialPaginatedInfoResponseMapper responseMapper;

    public Mono<InitialPaginatedInfoResponse> fetchRating(InitialPaginatedInfoRequest initialPaginatedInfoRequest)
    {
        return omdbService.searchPaginatedResults(initialPaginatedInfoRequest)
                .map(responseMapper::map)
                .checkpoint(getClass().getSimpleName());
    }
}
