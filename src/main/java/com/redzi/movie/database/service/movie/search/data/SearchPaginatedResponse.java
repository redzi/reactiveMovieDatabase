package com.redzi.movie.database.service.movie.search.data;

import lombok.Data;

import java.util.List;

@Data
public class SearchPaginatedResponse
{
    private List<SearchDetails> search;
    private long totalResults;
    private boolean response;
}
