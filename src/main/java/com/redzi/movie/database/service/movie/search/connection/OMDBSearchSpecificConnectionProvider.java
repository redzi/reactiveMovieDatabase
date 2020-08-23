package com.redzi.movie.database.service.movie.search.connection;

import com.redzi.movie.database.api.request.DetailedInfoRequest;
import com.redzi.movie.database.api.response.DetailedInfoResponse;
import com.redzi.movie.database.service.configuration.MovieServiceConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Function;

@Component
public class OMDBSearchSpecificConnectionProvider extends MovieDataConnectionProvider<DetailedInfoRequest, DetailedInfoResponse>
{
    private static final String SEARCH_PARAMETER = "i";
    private final EmptyResponseHandler<DetailedInfoResponse> emptyResponseHandler;

    public OMDBSearchSpecificConnectionProvider(WebClient webClient,
                                               MovieServiceConfiguration movieServiceConfiguration,
                                                EmptyResponseHandler<DetailedInfoResponse> emptyResponseHandler)
    {
        super(webClient, movieServiceConfiguration, DetailedInfoResponse.class);
        this.emptyResponseHandler = emptyResponseHandler;
    }

    @Override
    public Mono<DetailedInfoResponse> search(DetailedInfoRequest detailedInfoRequest)
    {
        return get(detailedInfoRequest)
                .log(this.getClass().getSimpleName())
                .handle(emptyResponseHandler)
                .checkpoint(this.getClass().getSimpleName() + " OMDB detailed search response.");
    }

    @Override
    protected Function<UriBuilder, URI> buildURI(DetailedInfoRequest detailedInfoRequest)
    {
        return uriBuilder -> uriBuilder
                .queryParam(SEARCH_PARAMETER, detailedInfoRequest.getImdbId())
                .queryParam(OMDB_API_KEY, movieServiceConfiguration.getApiKey())
                .build();
    }
}