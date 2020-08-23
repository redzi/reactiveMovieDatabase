package com.redzi.movie.database.api.request;

import com.redzi.movie.database.redis.KeyContainer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailedInfoRequest implements KeyContainer
{
    private String imdbId;

    @Override
    public String getKey()
    {
        return imdbId;
    }
}
