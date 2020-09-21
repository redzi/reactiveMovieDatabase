package com.redzi.movie.database.service.movie.search.mapper.calculator;

import com.redzi.movie.database.service.movie.search.data.SearchPaginatedResponse;
import io.vavr.Function1;
import org.springframework.stereotype.Component;

@Component
public class TotalPageNumberCalculator implements Function1<SearchPaginatedResponse, Integer>
{
    private static final Integer RESULTS_PER_PAGE = 10;

    @Override
    public Integer apply(SearchPaginatedResponse source)
    {
        return Math.toIntExact(source.getTotalResults()) / RESULTS_PER_PAGE;
    }
}
