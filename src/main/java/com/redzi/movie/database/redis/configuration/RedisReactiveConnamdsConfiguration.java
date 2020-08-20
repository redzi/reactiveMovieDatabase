package com.redzi.movie.database.redis.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.core.ReactiveListOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;

@Configuration
public class RedisReactiveConnamdsConfiguration
{
    @Bean
    public ReactiveListOperations<String, String> createRedisReactiveCommands(ReactiveStringRedisTemplate redisTemplate)
    {
        return redisTemplate.opsForList();
    }
}
