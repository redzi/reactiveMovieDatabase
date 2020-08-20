package com.redzi.movie.database.service.movie.search.mapper;

import com.redzi.movie.database.api.response.InitialPaginatedInfoResponse;
import com.redzi.movie.database.service.movie.mapper.Mapper;
import com.redzi.movie.database.service.movie.search.data.SearchPaginatedResponse;
import com.redzi.movie.database.service.movie.search.exception.NoDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitialPaginatedInfoResponseMapper implements Mapper<SearchPaginatedResponse, InitialPaginatedInfoResponse>
{
    @Autowired
    private SearchDetailsShortInfoMapper searchDetailsShortInfoMapper;

    @Override
    public InitialPaginatedInfoResponse map(SearchPaginatedResponse source)
    {
        if (!source.isResponse())
        {
            throw new NoDataException("No data returned.");
        }
        var response =  new InitialPaginatedInfoResponse();
        response.setInfos(searchDetailsShortInfoMapper.mapList(source.getSearch()));
        response.setTotalResultsNumber(source.getTotalResults());
        return response;
    }
}
