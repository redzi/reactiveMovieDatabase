package com.redzi.movie.database.service.movie.search;

import com.redzi.movie.database.api.request.DetailedInfoRequest;
import com.redzi.movie.database.api.request.InitialPaginatedInfoRequest;
import com.redzi.movie.database.api.response.DetailedInfoResponse;
import com.redzi.movie.database.redis.DetailedInfoRepository;
import com.redzi.movie.database.redis.SearchPaginatedResponseRepository;
import com.redzi.movie.database.api.response.SearchDetails;
import com.redzi.movie.database.service.movie.search.data.SearchPaginatedResponse;
import com.redzi.movie.database.service.movie.search.mapper.calculator.TotalPageNumberCalculator;
import io.vavr.collection.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class OMDBSearchService implements SearchService
{
    @Autowired
    private SearchPaginatedResponseRepository searchPaginatedResponseRepository;
    @Autowired
    private DetailedInfoRepository detailedInfoRepository;
    @Autowired
    private TotalPageNumberCalculator totalPageNumberCalculator;

    @Override
    public Flux<SearchDetails> searchPaginatedResults(InitialPaginatedInfoRequest initialPaginatedInfoRequest)
    {
        return searchPaginatedResponseRepository.getFromCacheOrLive(initialPaginatedInfoRequest)
                .log("Got search paginated results.")
                .checkpoint(getClass().getSimpleName())
                .map(totalPageNumberCalculator)
                .flatMapIterable(numberOfPages -> Stream.from(1).take(Math.toIntExact(numberOfPages)))
                .flatMap(page -> getSearchPaginatedResponseMono(initialPaginatedInfoRequest, page))
                .flatMapIterable(SearchPaginatedResponse::getSearch);

    }

    private Mono<SearchPaginatedResponse> getSearchPaginatedResponseMono(InitialPaginatedInfoRequest initialPaginatedInfoRequest, Integer page)
    {
        InitialPaginatedInfoRequest initPaginatedRequest = new InitialPaginatedInfoRequest(initialPaginatedInfoRequest.getName(), page);
        return searchPaginatedResponseRepository.getFromCacheOrLive(initPaginatedRequest);
    }

    @Override
    public Mono<DetailedInfoResponse> fetchSpecifics(DetailedInfoRequest detailedRequest)
    {
        return detailedInfoRepository.getFromCacheOrLive(detailedRequest);
    }
}
