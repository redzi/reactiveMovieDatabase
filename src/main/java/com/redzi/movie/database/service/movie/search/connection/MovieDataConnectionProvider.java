package com.redzi.movie.database.service.movie.search.connection;

import com.redzi.movie.database.api.request.InitialPaginatedInfoRequest;
import com.redzi.movie.database.service.configuration.MovieServiceConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Function;

public abstract class MovieDataConnectionProvider<T>
{
    protected final WebClient webClient;
    protected MovieServiceConfiguration movieServiceConfiguration;
    protected Class<T> resClazz;

    public MovieDataConnectionProvider(WebClient webClient,
                                       MovieServiceConfiguration movieServiceConfiguration,
                                       Class<T> resClazz)
    {
        this.webClient = webClient;
        this.movieServiceConfiguration = movieServiceConfiguration;
        this.resClazz = resClazz;
    }

    public abstract Mono<T> search(InitialPaginatedInfoRequest initialPaginatedInfoRequest);

    public Mono<T> get(InitialPaginatedInfoRequest initialPaginatedInfoRequest)
    {
        return webClient.get()
                .uri(buildURI(initialPaginatedInfoRequest))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(resClazz)
                .log(this.getClass().getSimpleName());
    }

    protected abstract Function<UriBuilder, URI> buildURI(InitialPaginatedInfoRequest initialPaginatedInfoRequest);
}
