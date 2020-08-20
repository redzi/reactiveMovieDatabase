package com.redzi.movie.database.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redzi.movie.database.service.movie.search.data.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ShortInfo
{
    private String title;
    private String year;
    @JsonProperty("imdbID")
    private String id;
    private Type type;
    @JsonProperty("poster")
    private String posterURL;
}
