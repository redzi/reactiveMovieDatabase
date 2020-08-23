package com.redzi.movie.database.service.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class JacksonConfiguration
{
    @Primary
    @Bean
    public ObjectMapper createObjectMapper()
    {
        ObjectMapper newMapper = new ObjectMapper();
        newMapper.registerModule(new JavaTimeModule());
        newMapper
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_VALUES)
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return newMapper;
    }

    @Bean
    public WebClient createWebClient(ObjectMapper objectMapper,
                                     WebClient.Builder webClientBuilder,
                                     ReactorClientHttpConnector reactorClientHttpConnector,
                                     MovieServiceConfiguration movieServiceConfiguration)
    {
        WebClient build = webClientBuilder
                .clientConnector(reactorClientHttpConnector)
                .baseUrl(movieServiceConfiguration.getBaseURL())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .codecs(configurer -> configurer.customCodecs().register(new Jackson2JsonDecoder(objectMapper)))
                .build();

        return build;
    }
}
