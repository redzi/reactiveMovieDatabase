package com.redzi.movie.database.service.movie.search.connection;

import com.redzi.movie.database.api.request.InitialPaginatedInfoRequest;
import com.redzi.movie.database.service.configuration.MovieServiceConfiguration;
import com.redzi.movie.database.service.movie.search.data.SearchPaginatedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Function;

@Slf4j
@Component
public class OMDBSearchConnectionProvider extends MovieDataConnectionProvider<SearchPaginatedResponse>
{
    private static final String OMDB_API_KEY = "apikey";
    public static final String SEARCH_PARAMETER = "s";
    public static final String PAGE_PARAMETER = "page";

    public OMDBSearchConnectionProvider(WebClient webClient,
                                        MovieServiceConfiguration movieServiceConfiguration)
    {
        super(webClient, movieServiceConfiguration, SearchPaginatedResponse.class);
    }

    @Override
    public Mono<SearchPaginatedResponse> search(InitialPaginatedInfoRequest initialPaginatedInfoRequest)
    {
        return get(initialPaginatedInfoRequest)
                .log(this.getClass().getSimpleName())
                .checkpoint(this.getClass().getSimpleName() + " OMDB paginated response.");
    }

    @Override
    protected Function<UriBuilder, URI> buildURI(InitialPaginatedInfoRequest initialPaginatedInfoRequest)
    {
        return uriBuilder -> uriBuilder
                .queryParam(SEARCH_PARAMETER, initialPaginatedInfoRequest.getName())
                .queryParam(PAGE_PARAMETER, initialPaginatedInfoRequest.getPage())
                .queryParam(OMDB_API_KEY, movieServiceConfiguration.getApiKey())
                .build();
    }
}
