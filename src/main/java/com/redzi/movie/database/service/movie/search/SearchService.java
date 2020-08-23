package com.redzi.movie.database.service.movie.search;

import com.redzi.movie.database.api.request.DetailedInfoRequest;
import com.redzi.movie.database.api.request.InitialPaginatedInfoRequest;
import com.redzi.movie.database.api.response.DetailedInfoResponse;
import com.redzi.movie.database.service.movie.search.data.SearchPaginatedResponse;
import reactor.core.publisher.Mono;

public interface SearchService
{
    Mono<SearchPaginatedResponse> searchPaginatedResults(InitialPaginatedInfoRequest initialPaginatedInfoRequest);
    Mono<DetailedInfoResponse> fetchSpecifics(DetailedInfoRequest detailedRequest);
}
