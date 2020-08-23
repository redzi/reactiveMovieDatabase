package com.redzi.movie.database.api.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitialPaginatedInfoResponse
{
    private List<ShortInfo> infos;
    private long totalResultsNumber;
    private long currentPageResultNumber;
}
