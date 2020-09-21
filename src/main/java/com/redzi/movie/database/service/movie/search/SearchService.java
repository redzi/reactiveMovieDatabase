package com.redzi.movie.database.service.movie.search;

import com.redzi.movie.database.api.request.DetailedInfoRequest;
import com.redzi.movie.database.api.request.InitialPaginatedInfoRequest;
import com.redzi.movie.database.api.response.DetailedInfoResponse;
import com.redzi.movie.database.api.response.SearchDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SearchService
{
    Flux<SearchDetails> searchPaginatedResults(InitialPaginatedInfoRequest initialPaginatedInfoRequest);
    Mono<DetailedInfoResponse> fetchSpecifics(DetailedInfoRequest detailedRequest);
}
