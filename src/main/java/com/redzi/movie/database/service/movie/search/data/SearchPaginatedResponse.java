package com.redzi.movie.database.service.movie.search.data;

import com.redzi.movie.database.api.response.ResponseContainer;
import lombok.Data;

import java.util.List;

@Data
public class SearchPaginatedResponse implements ResponseContainer
{
    private List<SearchDetails> search;
    private long totalResults;
    private boolean response;
}
