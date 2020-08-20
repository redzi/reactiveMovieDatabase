package com.redzi.movie.database.api.request;

import com.redzi.movie.database.redis.KeyContainer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitialPaginatedInfoRequest implements KeyContainer
{
    private String name;
    private Integer page;

    public String getKey()
    {
        var actualPage = page == null ? 1 : page;
        return name + actualPage;
    }
}
