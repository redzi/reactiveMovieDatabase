package com.redzi.movie.database.service.movie.search.mapper.calculator;

import com.redzi.movie.database.service.movie.search.data.SearchPaginatedResponse;
import io.vavr.Function2;
import io.vavr.control.Option;
import org.springframework.stereotype.Component;

@Component
public class CurrentPageResultNumberCalculator implements Function2<SearchPaginatedResponse, Integer, Integer>
{
    private static final Integer RESULTS_PER_PAGE = 10;
    private static final Integer FIRST_PAGE_NO = 1;

    @Override
    public Integer apply(SearchPaginatedResponse source, Integer pageNo)
    {
        var noOfResults = source.getSearch().size();
        return noOfResults + (Option.of(pageNo).getOrElse(FIRST_PAGE_NO) - 1) * RESULTS_PER_PAGE;
    }
}
