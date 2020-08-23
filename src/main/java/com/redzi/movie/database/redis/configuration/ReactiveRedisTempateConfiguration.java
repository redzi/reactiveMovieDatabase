package com.redzi.movie.database.redis.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redzi.movie.database.api.response.DetailedInfoResponse;
import com.redzi.movie.database.service.movie.search.data.SearchPaginatedResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class ReactiveRedisTempateConfiguration
{
    @Bean
    public ReactiveRedisTemplate<String, SearchPaginatedResponse> createReactiveRedisTemplateForSearchPaginatedResponse(
            ReactiveRedisConnectionFactory factory,
            ObjectMapper objectMapper)
    {
        var context = createContext(objectMapper, SearchPaginatedResponse.class);
        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean
    public ReactiveRedisTemplate<String, DetailedInfoResponse> createReactiveRedisTemplateForDetailedInfoResponse(
            ReactiveRedisConnectionFactory factory,
            ObjectMapper objectMapper)
    {
        var context = createContext(objectMapper, DetailedInfoResponse.class);
        return new ReactiveRedisTemplate<>(factory, context);
    }

    private <T> RedisSerializationContext<String, T> createContext(ObjectMapper objectMapper, Class<T> clazz)
    {
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<T> valueSerializer = new Jackson2JsonRedisSerializer<>(clazz);
        valueSerializer.setObjectMapper(objectMapper);
        RedisSerializationContext.RedisSerializationContextBuilder<String, T> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);
        return builder.value((valueSerializer)).build();
    }
}
