package com.redzi.movie.database.redis.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redzi.movie.database.service.movie.search.data.SearchDetails;
import com.redzi.movie.database.service.movie.search.data.SearchPaginatedResponse;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public ReactiveRedisTemplate<String, SearchPaginatedResponse> createReactiveRedisTemplate(
            ReactiveRedisConnectionFactory factory,
            ObjectMapper objectMapper)
    {
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<SearchPaginatedResponse> valueSerializer = new Jackson2JsonRedisSerializer<>(SearchPaginatedResponse.class);
        valueSerializer.setObjectMapper(objectMapper);
        RedisSerializationContext.RedisSerializationContextBuilder<String, SearchPaginatedResponse> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);
        RedisSerializationContext<String, SearchPaginatedResponse> context = builder.value((valueSerializer)).build();
        return new ReactiveRedisTemplate<>(factory, context);
    }
}
