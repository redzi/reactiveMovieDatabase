package com.redzi.movie.database.service.movie.search.mapper;

import com.redzi.movie.database.api.response.InitialPaginatedInfoResponse;
import com.redzi.movie.database.service.movie.mapper.ContextMapper;
import com.redzi.movie.database.service.movie.search.data.SearchPaginatedResponse;
import com.redzi.movie.database.service.movie.search.mapper.calculator.CurrentPageResultNumberCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitialPaginatedInfoResponseMapper implements ContextMapper<SearchPaginatedResponse, InitialPaginatedInfoResponse, Integer>
{
    @Autowired
    private SearchDetailsShortInfoMapper searchDetailsShortInfoMapper;
    @Autowired
    private CurrentPageResultNumberCalculator currentPageResultNumberCalculator;

    @Override
    public InitialPaginatedInfoResponse map(SearchPaginatedResponse source, Integer page)
    {
        var response =  new InitialPaginatedInfoResponse();
        response.setInfos(searchDetailsShortInfoMapper.mapList(source.getSearch()));
        response.setTotalResultsNumber(source.getTotalResults());
        response.setCurrentPageResultNumber(currentPageResultNumberCalculator.apply(source, page));
        return response;
    }
}
