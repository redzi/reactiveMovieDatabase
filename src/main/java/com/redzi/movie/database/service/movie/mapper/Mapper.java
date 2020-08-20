package com.redzi.movie.database.service.movie.mapper;


import java.util.List;

import static java.util.stream.Collectors.toList;

public interface Mapper<I, O>
{
    O map(I source);

    default List<O> mapList(List<I> sourceList)
    {
        return sourceList.stream()
                .map(this::map)
                .collect(toList());
    }
}
