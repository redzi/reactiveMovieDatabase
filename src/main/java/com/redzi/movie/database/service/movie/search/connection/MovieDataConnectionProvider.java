package com.redzi.movie.database.service.movie.search.connection;

import com.redzi.movie.database.service.configuration.MovieServiceConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Function;

public abstract class MovieDataConnectionProvider<T,R>
{
    protected static final String OMDB_API_KEY = "apikey";
    protected final WebClient webClient;
    protected MovieServiceConfiguration movieServiceConfiguration;
    protected Class<R> resClazz;

    public MovieDataConnectionProvider(WebClient webClient,
                                       MovieServiceConfiguration movieServiceConfiguration,
                                       Class<R> resClazz)
    {
        this.webClient = webClient;
        this.movieServiceConfiguration = movieServiceConfiguration;
        this.resClazz = resClazz;
    }

    public abstract Mono<R> search(T request);

    public Mono<R> get(T request)
    {
        return webClient.get()
                .uri(buildURI(request))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(resClazz)
                .log(this.getClass().getSimpleName());
    }

    protected abstract Function<UriBuilder, URI> buildURI(T request);
}
