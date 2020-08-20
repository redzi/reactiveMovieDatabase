package com.redzi.movie.database.redis.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "redis.server")
public class RedisTTLConfiguration
{
    private long secondsTTL;

    public long getSecondsTTL()
    {
        return secondsTTL;
    }

    public void setSecondsTTL(long secondsTTL)
    {
        this.secondsTTL = secondsTTL;
    }
}
