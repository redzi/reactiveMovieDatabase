package com.redzi.movie.database.service.movie.mapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface ContextMapper<I,O,C>
{
    O map(I source, C context);

    default List<O> mapList(List<I> source, C context)
    {
        return source.stream()
                .map(s -> map(s, context))
                .collect(toList());
    }
}
