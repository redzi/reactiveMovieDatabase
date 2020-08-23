package com.redzi.movie.database.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.redzi.movie.database.service.movie.search.data.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailedInfoResponse implements ResponseContainer
{
    private String title;
    private String year;
    private String rated;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d MMM u")
    private LocalDate released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private List<Rating> ratings;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    private Type type;
    private String boxOffice;
    private String production;
    private String website;
    private boolean response;
}
