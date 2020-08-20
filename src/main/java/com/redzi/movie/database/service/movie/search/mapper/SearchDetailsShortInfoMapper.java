package com.redzi.movie.database.service.movie.search.mapper;

import com.redzi.movie.database.api.response.ShortInfo;
import com.redzi.movie.database.service.movie.mapper.Mapper;
import com.redzi.movie.database.service.movie.search.data.SearchDetails;
import org.springframework.stereotype.Component;

@Component
public class SearchDetailsShortInfoMapper implements Mapper<SearchDetails, ShortInfo>
{
    @Override
    public ShortInfo map(SearchDetails source)
    {
        return ShortInfo.builder()
                .id(source.getId())
                .title(source.getTitle())
                .posterURL(source.getPosterURL())
                .year(source.getYear())
                .type(source.getType())
                .build();
    }
}
